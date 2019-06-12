package com.myproject.springcloud.controller;

import com.myproject.springcloud.entity.Dept;
import com.myproject.springcloud.service.DeptService;
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
    public Dept get(@PathVariable long id) {

        return deptService.get(id);
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

    @RequestMapping(value = "/dept/async", method = RequestMethod.GET)
    public String get() {
        deptService.asyncTask();
        System.out.println("成功");
        return "susses";
    }

}
