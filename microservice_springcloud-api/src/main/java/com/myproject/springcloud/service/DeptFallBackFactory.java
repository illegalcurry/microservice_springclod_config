package com.myproject.springcloud.service;

import com.myproject.springcloud.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeptFallBackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public List<Dept> list() {
                return null;
            }

            @Override
            public Dept get(long id) {
                return new Dept().setDno(id).setDName("这个id没有对应的对象 且dept服务端暂停使用").setDbSource("不存在数据库中");
            }
        };
    }
}
