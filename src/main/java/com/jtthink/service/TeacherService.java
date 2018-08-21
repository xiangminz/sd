package com.jtthink.service;


import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.Switch;
import com.jtthink.mapper.TeacherMapper;
import com.jtthink.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TeacherMapper teacherMapper;

    public void updateInitExamSubject(String subjectId){
        logger.info("==== TeacherService updateInitExamSubject beg ====");

        //初始化 exam_subject exam_detail 两个表
        int update_subject1 = teacherMapper.updateExamSubject();
        int update_subject2 = teacherMapper.updateExamSubject1(subjectId);
        int update_detail = teacherMapper.updateExamDetail(subjectId);

        logger.info("==== TeacherService updateInitExamSubject " +
                        "update_subject1 is {} update_subject2 is {} update_detail is {}  ====",
                update_subject1, update_subject2, update_detail);

        //初始化 exam_switch表
        String switch_key = ParamUtil.getConfigValue("exam.switch");
        String switch_value = ParamUtil.getConfigValue("exam.item"+subjectId);

        String[] switch_key_args = switch_key.split(",");
        String[] switch_value_args = switch_value.split(",");

        for(int i=0; i<switch_key_args.length; i++){
            teacherMapper.updateExamSwitch(switch_value_args[i], switch_key_args[i] );
        }

        logger.info("==== TeacherService updateInitExamSubject end ====");
    }

    public List<ExamDetail> getList(String detail_parent){
        logger.info("==== TeacherService getList beg ====");

        List<ExamDetail> list = teacherMapper.selectDetail(detail_parent);

        logger.info("==== TeacherService getList end ====");

        return list;
    }

    public List<Switch> getState(){
        logger.info("==== TeacherService getState beg ====");

        List<Switch> list = teacherMapper.selectSwitch();

        logger.info("==== TeacherService getState end ====");

        return list;
    }





}
