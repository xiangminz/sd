package com.jtthink.exception;

public class MyException extends Exception  {


    public String errNo;

    public String getErrNo() {
        return errNo;
    }

    public void setErrNo(String errNo) {
        this.errNo = errNo;
    }

    public MyException(String msg){
        super(msg);
    }

    public MyException(String message, String errNo) {
        super(message);
        this.errNo = errNo;
    }
}
