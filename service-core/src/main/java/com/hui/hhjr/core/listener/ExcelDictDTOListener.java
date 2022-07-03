package com.hui.hhjr.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hui.hhjr.core.mapper.DictMapper;
import com.hui.hhjr.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Excel读取监听类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/15 18:30
 * @since JDK8
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    /**
     * 数据列表
     */
    List<ExcelDictDTO> list = new ArrayList<>();

    /**
     * 每隔5条记录批量存储一次数据
     */
    private static final int BATCH_COUNT = 5;

    private DictMapper dictMapper;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条记录:{}",excelDictDTO);
        //将数据存入到数据列表
        list.add(excelDictDTO);
        //调用mapper层的save方法
        if (list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //当最后剩余的数据记录数不足BATCH_COUNT时，我们最终一次性存储剩余数据
        saveData();
        log.info("所有数据解析完成");
    }

    private void saveData(){
        log.info("{}条数据被存储到数据库。。。",list.size());
        //调用mapper层的save方法 :sava list对象
        dictMapper.insertBatch(list);
        log.info("{}条数据被存储到数据库成功。。。",list.size());
    }
}
