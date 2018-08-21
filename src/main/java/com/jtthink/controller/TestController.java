package com.jtthink.controller;

import com.jtthink.entity.UserInfo;
import com.jtthink.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    //====下面三个测请求
    @RequestMapping(value = "/test/{arg}", method= RequestMethod.GET)
    public String test(@PathVariable("arg") String arg){
        //get方式，后面参数可以输入任意值

        logger.trace("日志输出级别 trace");
        logger.debug("日志输出级别 debug");
        logger.info("日志输出级别 info");
        logger.warn("日志输出级别 warn");
        logger.error("日志输出级别 error");

        String str = arg;

        return str;
    }

    @RequestMapping(value = "/test1/{arg:\\d+}", method = RequestMethod.GET)
    public String test1(@PathVariable("arg") String arg){
        //get方式 参数必须是数字，否则页面报错

        String str = arg;
        System.out.println(str);

        return String.valueOf(str);
    }

    @RequestMapping(value = "/test2", method = RequestMethod.POST)
    public String test2(@RequestBody UserInfo userInfo){
        //post方式，传递json对象


        return userInfo.getUser_pass();
    }

    //下面两个测返回
    @RequestMapping(value = "/test3", method= RequestMethod.POST)
    public UserInfo test3(){
        //返回的json值为 {"name":"niushijin","pass":"yanghui"}

        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("niushijin");
        userInfo.setUser_pass("yanghui");

        return userInfo;
    }

    @RequestMapping(value = "/test4", method= RequestMethod.GET)
    public List<UserInfo> test4(){
        //返回的json值为
        //[{"name":"niushijin","pass":"yanghui"},{"name":"niushijin1","pass":"yanghui1"}]

        List<UserInfo> userInfoList = new ArrayList<UserInfo>();

        UserInfo userInfo = new UserInfo();
        userInfo.setUser_name("niushijin");
        userInfo.setUser_pass("yanghui");

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUser_name("niushijin1");
        userInfo1.setUser_pass("yanghui1");

        userInfoList.add(userInfo);
        userInfoList.add(userInfo1);

        return userInfoList;
    }



}
