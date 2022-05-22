package com.comments.commentSystem.config;

import com.comments.commentSystem.Custom.myLinks;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableRabbit
@EnableJpaAuditing
public class JmsConfig {

    @Bean
    Queue queue() {
        return new Queue(myLinks.exchangeQueue, false);
    }

    @Bean
    Queue getQueue() {
        return new Queue(myLinks.getExchangeQueue, false);
    }

    @Bean
    DirectExchange getExchange() {
        return new DirectExchange(myLinks.getExchange);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(myLinks.exchange);
    }

    @Bean
    Binding binding(Queue queue,DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(myLinks.routingKey);
    }

    @Bean
    Binding binding1(Queue getQueue,DirectExchange getExchange) {
        return BindingBuilder.bind(getQueue).to(getExchange).with(myLinks.routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
