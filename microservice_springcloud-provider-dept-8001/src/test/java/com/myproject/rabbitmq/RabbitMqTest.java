package com.myproject.rabbitmq;

import com.myproject.springcloud.DeptApplication8001;
import com.myproject.springcloud.entity.Dept;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeptApplication8001.class)
public class RabbitMqTest {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 单播模式发送消息
     * 默认使用java 序列化 修改成json方式
     * 使用json方式 重新注入 jackson2jsonMessageConverter
     */
    @Test
    public void sendDrict() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", "hello");
        map.put("arrays", Arrays.asList("testArray", true, 1564));
        map.put("dept", new Dept().setDno(1).setDName("测试").setDbSource("db1"));
        rabbitTemplate.convertAndSend("myproejct.direct", "myproject.new", map);

    }

    /**
     * 单播模式接收消息
     */
    @Test
    public void receiveDrict() {
        Object o = rabbitTemplate.receiveAndConvert("myproject.new");
        System.out.println(o.getClass());
        System.out.println(o.toString());

    }

    /**
     * 广播模式发送消息
     */
    @Test
    public void sendFanout() {
        Dept dept = new Dept().setDno(1).setDName("测试").setDbSource("db1");
        rabbitTemplate.convertAndSend("myproject.fanout", "", dept);
    }


    /**
     * 使用amqpadmin 创建 excahnge queue
     */
    @Test
    public void amqpAdminDeclare() {

        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.test"));
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",
                Binding.DestinationType.QUEUE, "amqpAdmin.test", "amqpAdmin", null));

    }

    /**
     * 使用amqpadmin 删除 excahnge queue
     */
    @Test
    public void amqpAdminDelete() {

        amqpAdmin.removeBinding(new Binding("amqpAdmin.queue",
                Binding.DestinationType.QUEUE, "amqpAdmin.test", "amqpAdmin", null));

        amqpAdmin.deleteQueue("amqpAdmin.queue");
        amqpAdmin.deleteExchange("amqpAdmin.test");

    }


}
