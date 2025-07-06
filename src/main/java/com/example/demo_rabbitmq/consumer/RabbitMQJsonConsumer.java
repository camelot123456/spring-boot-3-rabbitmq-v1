package com.example.demo_rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.demo_rabbitmq.dto.User;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RabbitMQJsonConsumer {
  
  @RabbitListener(queues = "${rabbitmq.queue-2.name}")
  public void consumeJsonMessage(User user) {
    log.info("JSON Message received from RabbitMQ: {}", user);
  }
}
