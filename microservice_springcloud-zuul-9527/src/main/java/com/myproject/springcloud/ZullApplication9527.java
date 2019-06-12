package com.myproject.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

import java.util.*;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy //zuul路由网关代理
public class ZullApplication9527{

    public static void main(String[] args){

        SpringApplication.run(ZullApplication9527.class, args);
    }

}
