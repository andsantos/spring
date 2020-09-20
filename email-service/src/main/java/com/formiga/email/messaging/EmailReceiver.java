package com.formiga.email.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formiga.email.model.Mail;
import com.formiga.email.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailReceiver {
	@Autowired
	private EmailService mailService;

	@RabbitListener(queues = "${rabbitmq.queue.mail}", containerFactory = "amqpFactory")
	public void receiveMessageFromTopic(Mail mail) {

		log.info("Recebendo mensagem do RabbitMQ...");

		mailService.enviarEmail(mail);
	}
}
