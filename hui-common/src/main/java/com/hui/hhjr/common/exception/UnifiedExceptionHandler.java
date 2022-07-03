package com.hui.hhjr.common.exception;

import com.hui.hhjr.common.result.Result;
import com.hui.hhjr.common.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 统一异常处理类
 *
 * @author hui 1154437939@qq.com
 * @version 2022/6/11 18:33
 * @since JDK8
 */
@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e){
        log.error(e.getMessage(),e);
        return Result.error();
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Result handlerException(BadSqlGrammarException e){
        log.error(e.getMessage(),e);
        return Result.setResult(null, ResultCodeEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result handlerException(BusinessException e){
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage(),e.getCode());
    }

    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handlerServletException(Exception e){
        log.error(e.getMessage(),e);
        return Result.error(ResultCodeEnum.SERVLET_ERROR.getMessage(),ResultCodeEnum.SERVLET_ERROR.getCode());
    }
}
