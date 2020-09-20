package com.formiga.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.formiga.email.model.Mail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void enviarEmail(Mail mensagem) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("formiga@formiga.com.br");
		mail.setTo(mensagem.getTo());
		mail.setSubject(mensagem.getSubject());
		mail.setText(mensagem.getText());

		javaMailSender.send(mail);

		log.info("E-mail enviado com sucesso !");
	}
}
