package com.example.demo_rabbitmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_rabbitmq.dto.User;
import com.example.demo_rabbitmq.publisher.RabbitMQJsonProducer;
import com.example.demo_rabbitmq.publisher.RabbitMQProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RabbitMQContainer {

  private final RabbitMQProducer rabbitMQProducer;
  private final RabbitMQJsonProducer rabbitMQJsonProducer;

  @GetMapping("/rabbitmq/publish")
  public String getMethodName(@RequestParam String message) {
    rabbitMQProducer.sendMessage(message);
    return "Message sent to RabbitMQ: " + message;
  }

  @PostMapping("/rabbitmq/publish")
  public String postMethodName(@RequestBody User user) {
      rabbitMQJsonProducer.sendJsonMessage(user);
      return "Message sent to RabbitMQ: " + user;
  }
  

}
