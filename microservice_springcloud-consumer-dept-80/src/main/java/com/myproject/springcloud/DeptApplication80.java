package com.myproject.springcloud;

import com.myproject.myrule.MyRibbonRule;
import com.myproject.myrule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICE-SPRINGCLOUD-DEPT",configuration = MyRule.class)
public class DeptApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptApplication80.class, args);
    }
}
