package com.myproject.springcloud.cfgbean;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestCfg {

    @Bean
    @LoadBalanced //实现客户端的负载均衡 默认使用轮询机制 当服务端有集群时要使用该注解来指定一个客户端
    public RestTemplate getRestTempLate(){
        return new RestTemplate();
    }

    /**
     * loadblanced 自定义负载均衡算法 可以实现IRule接口 可用其子类来修改算法
     */
//    @Bean
//    public IRule getIRule(){
//       return new RandomRule(); //使用随机方式 访问客户端
//       //return new RetryRule(); //默认使用轮询方式 当其中的服务端宕机时 会重试一定次数 还是失败时则会跳过失败的服务端
//    }


}
