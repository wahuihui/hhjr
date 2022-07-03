package com.hui.hhjr.core.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典模型实体类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/15 18:25
 * @since JDK8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDictDTO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("上级id")
    private Long parentId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("值")
    private Integer value;

    @ExcelProperty("编码")
    private String dictCode;

}
