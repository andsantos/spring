package com.formiga.email.service;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.formiga.email.EmailServiceApplication;
import com.formiga.email.model.Mail;

@SpringBootTest(classes = EmailServiceApplication.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
@AutoConfigureMockMvc
public class RabbitMQTest {
	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${rabbitmq.exchange.mail}")
	private String exchange;

	@Value("${rabbitmq.routing.key.mail}")
	private String routingKey;

	@Test
	void enviarEmail() {
		Mail mensagem = new Mail();
		mensagem.setSubject("Teste RabbitMQ");
		mensagem.setTo("teste@rabbitmq.com.br");
		mensagem.setText("Email enviado pelo rabbitmq");

		amqpTemplate.convertAndSend(exchange, "", mensagem);
	}
}
