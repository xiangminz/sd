package com.jtthink.controller;

import com.jtthink.entity.UserInfo;
import com.jtthink.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(@RequestBody UserInfo userInfo){
        logger.info("==== UserController login beg ====");

        boolean flag;
        flag = userService.login(userInfo);

        logger.info("==== UserController login end ====");
        return flag;
    }





}
