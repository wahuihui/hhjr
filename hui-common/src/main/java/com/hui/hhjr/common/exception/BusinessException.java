package com.hui.hhjr.common.exception;

import com.hui.hhjr.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/12 13:31
 * @since JDK8
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误消息
     */
    private String message;

    /**
     * @param message 错误消息
     */
    public BusinessException(String message){
        this.message = message;
    }

    /**
     * @param message 错误消息
     * @param code 错误码
     */
    public BusinessException(String message,Integer code){
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param message 错误消息
     * @param code 错误码
     * @param cause 原始异常对象
     */
    public BusinessException(String message,Integer code,Throwable cause){
        super(cause);
        this.message = message;
        this.code = code;
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     */
    public BusinessException(ResultCodeEnum resultCodeEnum){
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

    /**
     *
     * @param resultCodeEnum 接收枚举类型
     * @param cause 原始异常对象
     */
    public BusinessException(ResultCodeEnum resultCodeEnum,Throwable cause){
        super(cause);
        this.message = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }

}
