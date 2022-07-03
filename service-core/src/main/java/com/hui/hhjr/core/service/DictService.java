package com.hui.hhjr.core.service;

import com.hui.hhjr.core.pojo.dto.ExcelDictDTO;
import com.hui.hhjr.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author AoHui
 * @since 2022-06-10
 */
public interface DictService extends IService<Dict> {


    void importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);
}
