package com.example.demo_rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RabbitMQConsumer {
  
  @RabbitListener(queues = "${rabbitmq.queue-1.name}")
  public void consumeMessage(String message) {
    log.info("Message received from RabbitMQ: {}", message);
  }

}
