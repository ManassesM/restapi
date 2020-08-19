package com.manadev.services;

import org.springframework.mail.SimpleMailMessage;

import com.manadev.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
