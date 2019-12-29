package com.ciro.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.ciro.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
