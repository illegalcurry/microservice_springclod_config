package com.myproject.springcloud.service.impl;

import com.myproject.springcloud.dao.DeptDao;
import com.myproject.springcloud.entity.Dept;
import com.myproject.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public Dept get(long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> List() {
        return deptDao.findList();
    }

    @Override
    public boolean add(Dept dept) {
        return deptDao.add(dept);
    }
}
