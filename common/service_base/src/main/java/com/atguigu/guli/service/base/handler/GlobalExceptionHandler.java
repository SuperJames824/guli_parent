package com.atguigu.guli.service.base.handler;

import com.atguigu.guli.common.base.result.R;
import com.atguigu.guli.common.base.result.ResultCodeEnum;

import com.atguigu.guli.common.base.util.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author chengfei huang
 *
 * 异常处理（拦截器的方式）
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    //统一错误
    @ExceptionHandler(Exception.class)//捕获的错误的类型 .class
    @ResponseBody
    public R error(Exception e){
        log.error(e.getMessage(),e.fillInStackTrace());
        return R.error();
    }


    //特殊错误
    @ExceptionHandler(BadSqlGrammarException.class)//捕获的错误的类型 .class
    @ResponseBody
    public R errorBadSqlGrammarException(BadSqlGrammarException e){
//        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }



}
