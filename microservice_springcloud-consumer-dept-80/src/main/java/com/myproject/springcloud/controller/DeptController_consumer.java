package com.myproject.springcloud.controller;

import com.myproject.springcloud.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/dept")
public class DeptController_consumer {

    //private static final String REST_URL_BASE = "http://localhost:8001";
    //通过eureka实例名称访问服务端 对应客服端的配置文件
    private static final String REST_URL_BASE = "http://MICROSERVICE-SPRINGCLOUD-DEPT";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_BASE+"/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/get/{id}")
    public Dept get(@PathVariable long id){
        return restTemplate.getForObject(REST_URL_BASE + "/dept/get/"+id, Dept.class);
    }

    @RequestMapping("/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_BASE+"/dept/list",List.class);
    }
    @RequestMapping("/discovery")
    public Object discovery(){

        return restTemplate.getForObject(REST_URL_BASE + "/dept/discovery", Object.class);
    }

}
