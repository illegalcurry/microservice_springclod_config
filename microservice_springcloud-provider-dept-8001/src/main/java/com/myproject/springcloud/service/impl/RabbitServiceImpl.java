package com.myproject.springcloud.service.impl;

import com.myproject.springcloud.entity.Dept;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitServiceImpl {


    @RabbitListener(queues = "myproject.new")
    public void receive(Message message, Dept dept){
        System.out.println(message.getBody());
        System.out.println(dept);
        System.out.println(message.getMessageProperties());
    }




}
