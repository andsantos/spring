package com.formiga.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formiga.email.service.RabbitMQService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmailRabbitController {

	@Autowired
	private RabbitMQService rabbit;

	@GetMapping(path = "/rabbit")
	public ResponseEntity<Boolean> enviarEmail() {
		try {
			String para = "teste@teste.com.br";
			String assunto = "E-mail para o Rabbit";
			String texto = "Este texto será passará pelo RabbitMQ";
			
			rabbit.enviarEmailPeloRabbit(para, assunto, texto);
			return ResponseEntity.ok().body(Boolean.TRUE);
		} catch (Exception e) {
			log.error("Erro ao enviar e-mail: ", e);
			return ResponseEntity.ok().body(Boolean.FALSE);
		}


	}
}
