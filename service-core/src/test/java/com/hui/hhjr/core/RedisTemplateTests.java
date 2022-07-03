package com.hui.hhjr.core;

import com.hui.hhjr.core.mapper.DictMapper;
import com.hui.hhjr.core.pojo.entity.Dict;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hui 1154437939@qq.com
 * @version 2022/6/17 18:39
 * @since JDK8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private DictMapper dictMapper;

    @Test
    public void saveDict(){
        Dict dict = dictMapper.selectById(1);
        redisTemplate.opsForValue().set("dict",dict,5, TimeUnit.MINUTES);
    }

    @Test
    public void getDict(){
        Dict dict = (Dict) redisTemplate.opsForValue().get("dict");
        System.out.println(dict);
    }
}
