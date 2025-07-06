package com.example.demo_rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class RabbitMQProducer {

  @Value("${rabbitmq.exchange.name}")
  public String exchange;

  @Value("${rabbitmq.routing-key}")
  public String routingKey;

  private final RabbitTemplate rabbitTemplate;

  public void sendMessage(String message) {
    log.info("Sending message: {}", message);
    rabbitTemplate.convertAndSend(exchange, routingKey, message);
  }
}
