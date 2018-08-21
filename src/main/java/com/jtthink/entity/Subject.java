package com.jtthink.entity;

public class Subject {

    private int subject_id;
    private String subject_name;
    private int subject_num; //二级题目数
    private int subject_use;  //0 不用 1 在用
    private int subject_type;
    private String subject_backup1; //题目编号
    private String subject_backup2; //三级题目数


    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getSubject_num() {
        return subject_num;
    }

    public void setSubject_num(int subject_num) {
        this.subject_num = subject_num;
    }

    public int getSubject_use() {
        return subject_use;
    }

    public void setSubject_use(int subject_use) {
        this.subject_use = subject_use;
    }

    public int getSubject_type() {
        return subject_type;
    }

    public void setSubject_type(int subject_type) {
        this.subject_type = subject_type;
    }

    public String getSubject_backup1() {
        return subject_backup1;
    }

    public void setSubject_backup1(String subject_backup1) {
        this.subject_backup1 = subject_backup1;
    }

    public String getSubject_backup2() {
        return subject_backup2;
    }

    public void setSubject_backup2(String subject_backup2) {
        this.subject_backup2 = subject_backup2;
    }
}
