package com.jtthink.entity;

public class ErrorResult {

    private String errNo;
    private String errMsg;
    private int status;

    public ErrorResult(String errNo, String errMsg, int status) {
        this.errNo = errNo;
        this.errMsg = errMsg;
        this.status = status;
    }

    public String getErrNo() {
        return errNo;
    }

    public void setErrNo(String errNo) {
        this.errNo = errNo;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
