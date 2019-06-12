package com.myproject.springcloud.controller;

import com.myproject.springcloud.entity.Dept;
import com.myproject.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "exceptionFallBackMethod") //使用hystrix熔断机制 当方法抛出异常 则会调用fallback方法
    public Dept get(@PathVariable long id) {
        Dept dept = deptService.get(id);
        if(dept == null){
            System.out.println("11");
            throw new RuntimeException("没找到对应的对象");
        }
        return dept;
    }

    public Dept exceptionFallBackMethod(@PathVariable long id) {
        return new Dept().setDno(id).setDName("这个id没有对应的对象").setDbSource("不存在数据库中");
    }


    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> List() {
        new Dept();
        return deptService.List();

    }

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = discoveryClient.getServices();
        System.out.println("*****" + list);
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("microservice_springcloud-dept");
        serviceInstances.forEach((serviceInstance) -> {
            System.out.println(serviceInstance.getHost() + "----" + serviceInstance.getPort() + "---" + serviceInstance.getUri());
        });
        return discoveryClient;
    }

}
