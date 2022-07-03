package com.hui.hhjr.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hui.hhjr.core.listener.ExcelDictDTOListener;
import com.hui.hhjr.core.pojo.dto.ExcelDictDTO;
import com.hui.hhjr.core.pojo.entity.Dict;
import com.hui.hhjr.core.mapper.DictMapper;
import com.hui.hhjr.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }

    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dictList = baseMapper.selectList(null);
        List<ExcelDictDTO> excelDictDTOList = new ArrayList<>();
        for (Dict dict : dictList) {
            excelDictDTOList.add(new ExcelDictDTO(dict.getId(), dict.getParentId(), dict.getName(), dict.getValue(), dict.getDictCode()));
        }
        return excelDictDTOList;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {

        //首先查询redis中是否存在数据列表
        try {
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("hhjr:core:dictList:"+parentId);
            if (dictList!=null){
                //如果存在则从redis中直接返回数据列表
                log.info("从redis中获取数据列表");
                return dictList;
            }
        } catch (Exception e) {
            log.info("redis服务器异常：" + ExceptionUtils.getStackTrace(e));
        }

        //如果不存在则查询数据库
        log.info("从数据库中获取数据列表");
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id",parentId);
        List<Dict> dictList = baseMapper.selectList(dictQueryWrapper);
        //填充hashChildren字段
        for (Dict dict : dictList) {
            //判断当前节点是否有子节点，找到当前的dict下级有没有子节点
            boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        }
        try {
            //将数据存入redis
            log.info("将数据存入redis");
            redisTemplate.opsForValue().set("hhjr:core:dictList:"+parentId,dictList,5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.info("redis服务器异常：" + ExceptionUtils.getStackTrace(e));
        }

        //返回数据列表
        return dictList;
    }

    /**
     * 判断当前id所在的节点下是否有子节点
     * @param id Dict的id
     * @return 是否大于零
     */
    private boolean hasChildren(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count >0;
    }
}
