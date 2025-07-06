package com.example.demo_rabbitmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_rabbitmq.publisher.RabbitMQProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RabbitMQContainer {

  private final RabbitMQProducer rabbitMQProducer;

  @GetMapping("/rabbitmq/publish")
  public String getMethodName(@RequestParam String message) {
    rabbitMQProducer.sendMessage(message);
    return "Message sent to RabbitMQ: " + message;
  }

}
