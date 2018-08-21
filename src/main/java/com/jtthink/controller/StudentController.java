package com.jtthink.controller;

import com.jtthink.entity.QuestionResult;
import com.jtthink.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/answer/{arg}", method= RequestMethod.GET)
    public QuestionResult ques(@PathVariable("arg") String key){
        //get方式，后面参数可以输入任意值

        QuestionResult qr = studentService.getResultSequence(key);

        return qr;
    }

    @RequestMapping(value = "/answergroup/{arg}", method= RequestMethod.GET)
    public QuestionResult quesGroup(@PathVariable("arg") String key){
        //get方式，后面参数可以输入任意值

        QuestionResult qr = studentService.getResultSequence(key);

        return qr;
    }






}
