package com.example.demo_rabbitmq.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo_rabbitmq.dto.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

  @Value("${rabbitmq.exchange.name}")
  public String exchange;

  @Value("${rabbitmq.routing-2.key}")
  public String routingKey;

  @Value("${rabbitmq.queue-2.name}")
  public String jsonQueue;

  private final RabbitTemplate rabbitTemplate;

  public void sendJsonMessage(User user) {
    rabbitTemplate.convertAndSend(exchange, routingKey, user);
    System.out.println("Message sent to RabbitMQ: " + user);
  }
}
