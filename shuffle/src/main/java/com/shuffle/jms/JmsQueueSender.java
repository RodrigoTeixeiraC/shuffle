package com.shuffle.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class JmsQueueSender {

	private JmsTemplate jmsTemplate;

	public void simpleSend(String queue, TextMessage message) {
		jmsTemplate.send(queue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage convertedMessage = message;
				return convertedMessage;
			}
		});

	}

}
