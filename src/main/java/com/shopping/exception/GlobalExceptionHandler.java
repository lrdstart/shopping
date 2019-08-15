package com.shopping.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于捕获全局异常
 * @ControllerAdvice控制器切面
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //处理异常方法

    /**
     * @ExceptionHandler捕获运行时异常
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> excetionHandler(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("errorCode","101");
        map.put("errorMsg","系统错误！");
        return map;
    }
}
