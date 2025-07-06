package com.example.demo_rabbitmq.config;

import java.sql.Connection;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.queue-1.name}")
  private String queue;

  @Value("${rabbitmq.queue-2.name}")
  private String jsonQueue;

  @Value("${rabbitmq.exchange.name}")
  private String exchange;

  @Value("${rabbitmq.routing-1.key}")
  private String routingKey;

  @Value("${rabbitmq.routing-2.key}")
  private String jsonRoutingKey;

  @Bean
  public Queue queue() {
    return new Queue(queue);
  }

  @Bean
  public Queue jsonQueue() {
    return new Queue(jsonQueue);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(exchange);
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder
        .bind(queue())
        .to(exchange())
        .with(routingKey);
  }

  @Bean
  public Binding jsonBinding(Queue jsonQueue, TopicExchange exchange) {
    return BindingBuilder
        .bind(jsonQueue())
        .to(exchange())
        .with(jsonRoutingKey);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(converter());
    return rabbitTemplate;
  }

}
