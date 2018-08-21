package com.jtthink.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParamConfig {

    @Value(value = "${exam.switch}")
    private String examSwitch;

    @Value(value = "${exam.item1}")
    private String examItem1;

    @Value(value = "${exam.item2}")
    private String examItem2;




}
