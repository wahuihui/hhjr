package com.hui.hhjr.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 响应结果
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/11 12:01
 * @since JDK8
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * 私有化构造器
     */
    private Result(){}

    /**
     * 设置特定的结果
     * @param resultCodeEnum 结果状态信息类
     * @param <T> 数据类型
     * @return 结果
     */
    public static<T> Result<T> setResult(T data,ResultCodeEnum resultCodeEnum){
        return setResult(data,resultCodeEnum.getMessage(), resultCodeEnum.getCode());
    }

    public static<T> Result<T> setResult(T data,String message,ResultCodeEnum resultCodeEnum){
        return setResult(data,message,resultCodeEnum.getCode());
    }

    public static<T> Result<T> setResult(T data,String message,Integer code){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMessage(message);
        result.setCode(code);
        return result;
    }

    public static<T> Result<T> ok(T data){
        return setResult(data,ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(T data,String message){
        return setResult(data,message,ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(){
        return ok(null);
    }

    public static<T> Result<T> ok(String message){
        return ok(null,message);
    }

    public static<T> Result<T> error(){
        return setResult(null,ResultCodeEnum.ERROR);
    }

    public static<T> Result<T> error(String message){
        return setResult(null,message,ResultCodeEnum.ERROR);
    }

    public static<T> Result<T> error(String message,Integer code){
        return setResult(null,message,code);
    }
}
