package com.myproject.springcloud.service;

import com.myproject.springcloud.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "MICROSERVICE-SPRINGCLOUD-DEPT",fallbackFactory = DeptFallBackFactory.class)
public interface DeptClientService {

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    boolean add(Dept dept);

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    List<Dept> list();

    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
    Dept get(@PathVariable long id);
}
