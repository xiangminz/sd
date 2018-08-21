package com.jtthink.controller;

import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.Switch;
import com.jtthink.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TeacherService teacherService;


    @RequestMapping(value = "/num/{arg}", method= RequestMethod.GET)
    public String test(@PathVariable("arg") String id){
        //get方式，后面参数可以输入任意值

        String subjectId = id;
        teacherService.updateInitExamSubject(subjectId);

        return "OK";
    }

    @RequestMapping(value = "/search/{arg}", method= RequestMethod.GET)
    public List<ExamDetail> getDetail(@PathVariable("arg") String id){

        List<ExamDetail> list = new ArrayList<ExamDetail>();
        list = teacherService.getList(id);

        return list;
    }

    @RequestMapping(value = "/circuit", method= RequestMethod.GET)
    public List<Switch> getCircuitState(){

        List<Switch> list = new ArrayList<Switch>();
        list= teacherService.getState();

        return list;
    }


}
