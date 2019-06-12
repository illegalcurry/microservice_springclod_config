package com.myproject.springcloud.service;

import com.myproject.springcloud.entity.Dept;

import java.util.List;

public interface DeptService {

    public Dept get(long id);

    public List<Dept> List();

    public boolean add(Dept dept);

    public void asyncTask();

}
