package com.myproject.springcloud.service.impl;

import com.myproject.springcloud.dao.DeptDao;
import com.myproject.springcloud.entity.Dept;
import com.myproject.springcloud.service.DeptService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames="dept")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    //@Cacheable(cacheNames = {"dept"},key = "#id")
    @Caching(
            cacheable = {
                    @Cacheable(cacheNames = {"dept"}, key = "#id")
            },
            put = {
                    @CachePut(cacheNames = {"dept"}, key = "#result.dName"),
                    @CachePut(cacheNames = {"dept"}, key = "#result.dbSource")
            }
    )
    public Dept get(long id) {
        return deptDao.findById(id);
    }

    @Override
    public List<Dept> List() {
        return deptDao.findList();
    }

    @Override
    @CachePut(value = "dept", key = "#dept.dno")
    public boolean add(Dept dept) {
        return deptDao.add(dept);
    }

    @Override
    @Async
    public void asyncTask() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行异步任务");


    }


}
