package com.myproject.elasticsearch;


import com.myproject.springcloud.DeptApplication8001;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeptApplication8001.class)
public class MyESSpringTest {

//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
//    @Test
//    public void TestES01() throws IOException {
//        System.out.println(elasticsearchTemplate.toString());
//
//        Employee employee = new Employee().setEno(1).setEName("测试员工1").setDbSource("db1");
//        IndexQuery indexQuery = new IndexQueryBuilder().withId(employee.getEno()+"").withObject(employee).build();
//        indexQuery.setIndexName("myproject");
//        indexQuery.setType("emp");
//        elasticsearchTemplate.index(indexQuery);
//
//
//
//    }

    @Test
    public void TestES02() throws IOException {

    }


}
