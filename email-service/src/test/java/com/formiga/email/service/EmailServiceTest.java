package com.formiga.email.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.formiga.email.EmailServiceApplication;
import com.formiga.email.model.Mail;

@SpringBootTest(classes = EmailServiceApplication.class)
//@ContextConfiguration(classes = DemoApplication.class, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
@AutoConfigureMockMvc
public class EmailServiceTest {
	@Autowired
	private EmailService mailService;

	@Test
	void enviarEmail() {
		Mail mensagem = new Mail();
		mensagem.setSubject("Teste");
		mensagem.setTo("teste@teste.com.br");
		mensagem.setText("Corpo de e-mail");

		mailService.enviarEmail(mensagem);
	}
}
