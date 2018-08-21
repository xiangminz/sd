package com.jtthink.entity;

public class QuestionResult {

    private String resultFlag; // 0-错误question.right 1-正确question.wrong 2-顺序错误question.seq.wrong
    private String resultMsg;

    public QuestionResult(String resultFlag, String resultMsg){
        this.resultFlag = resultFlag;
        this.resultMsg = resultMsg;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
