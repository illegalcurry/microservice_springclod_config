package com.myproject.elasticsearch;


import com.myproject.springcloud.DeptApplication8001;
import com.myproject.springcloud.entity.Dept;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeptApplication8001.class)
public class MyESJestTest {

    @Autowired
    JestClient jestClient;

    @Test
    public void TestES01() throws IOException {

        Dept dept = new Dept().setDno(4).setDName("测试Es4").setDbSource("db01");
        Index index = new Index.Builder(dept).index("myproject").type("dept").id("dno").build();
        jestClient.execute(index);

    }

    @Test
    public void TestES02() throws IOException {

        Search build = new Search.Builder("{\n" +
                "    \"query\": {\n" +
                "        \"match\": {\n" +
                "            \"dno\": 4\n" +
                "        }\n" +
                "    }\n" +
                "}")
                .addIndex("myproject").addType("dept").build();
        SearchResult result = jestClient.execute(build);
        System.out.println(result.getJsonString());
        SearchResult.Hit<Dept, Void> firstHit = result.getFirstHit(Dept.class);
        Dept dept = firstHit.source;

        System.out.println(dept.toString());
    }


}
