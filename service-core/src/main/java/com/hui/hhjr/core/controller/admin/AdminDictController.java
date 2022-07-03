package com.hui.hhjr.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.hui.hhjr.common.exception.BusinessException;
import com.hui.hhjr.common.result.Result;
import com.hui.hhjr.common.result.ResultCodeEnum;
import com.hui.hhjr.core.pojo.dto.ExcelDictDTO;
import com.hui.hhjr.core.pojo.entity.Dict;
import com.hui.hhjr.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
@CrossOrigin
public class AdminDictController {

    @Resource
    private DictService dictService;

    @ApiOperation("Excel数据的批量导入")
    @PostMapping("/import")
    public Result batchImport(
            @ApiParam(value = "Excel数据字典文件",required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);

            return Result.ok("数据字典数据批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResultCodeEnum.UPLOAD_ERROR,e);
        }
    }

    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //这里的URLEncoding.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("mydict","UTF-8").replaceAll("\\+","%20");
        response.setHeader("Content-disposition","attachment;filename*=utf-8''" + fileName +".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public Result listByParentId(
            @ApiParam(value = "上级节点id",required = true)
            @PathVariable Long parentId
    ){
        List<Dict> dictList = dictService.listByParentId(parentId);
        return Result.ok(dictList);
    }

}

