package com.jtthink.config;

import com.jtthink.entity.ErrorResult;
import com.jtthink.exception.MyException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

public class MyHandler {


    @ExceptionHandler(value = MyException.class)
    public ErrorResult defaultHandler(MyException ex, HttpServletResponse rs){

        /*
        * throw new MyException("自定义异常", "10010");
        * 可在controller中返回一个异常编号
        * */

        ErrorResult errorResult =
                new ErrorResult(ex.getErrNo(), ex.getMessage(), rs.getStatus());
        return errorResult;

    }





}
