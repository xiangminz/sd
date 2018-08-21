package com.jtthink.service;

import com.jtthink.entity.UserInfo;
import com.jtthink.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    public Boolean login(UserInfo userInfoParam){
        logger.info("==== UserService login beg ====");

        UserInfo userInfo = userMapper.selectUser(userInfoParam);
        logger.info("==== UserService login userInfo {} ====", (userInfo!=null? userInfo.toString():"") );
        logger.info("==== UserService login end ====");

        return userInfo == null ? false : true;

    }


}
