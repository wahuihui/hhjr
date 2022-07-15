package com.hui.hhjr.core.controller.admin;


import com.hui.hhjr.common.exception.Assert;
import com.hui.hhjr.common.result.Result;
import com.hui.hhjr.common.result.ResultCodeEnum;
import com.hui.hhjr.core.pojo.entity.IntegralGrade;
import com.hui.hhjr.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
@Api(tags = "积分等级管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
@Slf4j
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public Result<List<IntegralGrade>> listAll() {
        log.info("/list .........................");
        List<IntegralGrade> integralGradeList = integralGradeService.list();
        return Result.ok(integralGradeList);
    }

    @ApiOperation(value = "根据id删除对应积分等级信息",notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public Result removeById(
            @ApiParam("需要删除积分等级id")
            @PathVariable Long id
    ) {
        boolean isRemove = integralGradeService.removeById(id);
        if (isRemove){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }

    @ApiOperation("添加积分等级")
    @PostMapping("/save")
    public Result save(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade
    ){

        Assert.notNull(integralGrade.getBorrowAmount(), ResultCodeEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean isSave = integralGradeService.save(integralGrade);
        if (isSave){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @ApiOperation("修改积分等级")
    @PutMapping("/update")
    public Result update(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade
    ){
        boolean isUpdate = integralGradeService.updateById(integralGrade);
        if (isUpdate){
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

    @ApiOperation("根据id获取对应积分等级信息")
    @GetMapping("/get/{id}")
    public Result<IntegralGrade> getById(
            @ApiParam(value = "需要获取的积分等级id",required = true)
            @PathVariable Integer id
    ){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade!=null){
            return Result.ok(integralGrade);
        }
        return Result.error("未找到该积分等级");
    }

}

