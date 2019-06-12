package com.myproject.springcloud.dao;

import com.myproject.springcloud.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {

    public Dept findById(long id);

    public List<Dept> findList();

    public boolean add(Dept dept);


}
