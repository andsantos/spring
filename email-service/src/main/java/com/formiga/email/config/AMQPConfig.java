package com.formiga.email.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

	@Value("${rabbitmq.queue.mail}")
	private String QUEUE_NAME;

	@Value("${rabbitmq.exchange.mail}")
	private String EXCHANGE_NAME;

	@Value("${rabbitmq.routing.key.mail}")
	private String ROUTING_KEY;

    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    public Queue declareQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .build();
    }

    @Bean
    public Binding declareBinding(Exchange exchange, Queue queue) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleRabbitListenerContainerFactory amqpFactory(ConnectionFactory connectionFactory,
			SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setMessageConverter(jsonMessageConverter());
		return factory;
	}
}
