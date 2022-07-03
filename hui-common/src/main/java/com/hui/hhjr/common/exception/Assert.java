package com.hui.hhjr.common.exception;

import com.hui.hhjr.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 自定义断言类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/13 11:07
 * @since JDK8
 */
@Slf4j
public class Assert {

    /**
     * 断言对象不能为空 object为空则抛出异常
     * @param object 断言对象
     * @param resultCodeEnum 接收枚举类型
     */
    public static void notNull(Object object, ResultCodeEnum resultCodeEnum){
        if (object == null){
            log.info("object is null................");
            throw new BusinessException(resultCodeEnum);
        }
    }

    /**
     * 断言对象为空
     * 如果对象obj不为空，则抛出异常
     * @param object 断言对象
     * @param resultCodeEnum 接收枚举类型
     */
    public static void isNull(Object object, ResultCodeEnum resultCodeEnum) {
        if (object != null) {
            log.info("obj is not null......");
            throw new BusinessException(resultCodeEnum);
        }
    }

    /**
     * 断言表达式为真
     * 如果不为真，则抛出异常
     * @param expression 是否成功
     */
    public static void isTrue(boolean expression, ResultCodeEnum resultCodeEnum) {
        if (!expression) {
            log.info("fail...............");
            throw new BusinessException(resultCodeEnum);
        }
    }

    /**
     * 断言两个对象不相等
     * 如果相等，则抛出异常
     * @param m1 第一个对象
     * @param m2 第二个对象
     * @param resultCodeEnum 接收枚举类型
     */
    public static void notEquals(Object m1, Object m2,  ResultCodeEnum resultCodeEnum) {
        if (m1.equals(m2)) {
            log.info("equals...............");
            throw new BusinessException(resultCodeEnum);
        }
    }

    /**
     * 断言两个对象相等
     * 如果不相等，则抛出异常
     * @param m1 第一个对象
     * @param m2 第二个对象
     * @param resultCodeEnum 接收枚举类型
     */
    public static void equals(Object m1, Object m2,  ResultCodeEnum resultCodeEnum) {
        if (!m1.equals(m2)) {
            log.info("not equals...............");
            throw new BusinessException(resultCodeEnum);
        }
    }

    /**
     * 断言参数不为空
     * 如果为空，则抛出异常
     * @param s 字符串参数
     * @param resultCodeEnum 接收枚举类型
     */
    public static void notEmpty(String s, ResultCodeEnum resultCodeEnum) {
        if (StringUtils.isEmpty(s)) {
            log.info("is empty...............");
            throw new BusinessException(resultCodeEnum);
        }
    }
}
