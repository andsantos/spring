package com.formiga.email.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.formiga.email.model.Mail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQService {
	@Value("${rabbitmq.queue.mail}")
	private String QUEUE_NAME;

	@Value("${rabbitmq.exchange.mail}")
	private String EXCHANGE_NAME;

	@Value("${rabbitmq.routing.key.mail}")
	private String ROUTING_KEY;

	@Autowired
    private RabbitTemplate rabbitTemplate;

	public void enviarEmailPeloRabbit(String para, String assunto, String texto) {
		Mail mail = new Mail();
		mail.setTo(para);
		mail.setSubject(assunto);
		mail.setText(texto);

		log.info("Enviando mensagem para o RabbitMQ...");

		rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mail);

		log.info("E-mail enviado para o RabbitMQ !");
	}
}
