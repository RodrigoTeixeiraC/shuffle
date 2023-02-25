package com.shuffle.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

import com.shuffle.GerenciadorFila;

@Configuration
@EnableJms
public class AppConfig implements JmsListenerConfigurer {

	@Value("${queueInformationList}")
	private String[] queuesInformationList;

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {

		int i = 0;
		for (String queueInformation : queuesInformationList) {
			SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
			endpoint.setId("myJmsEndpoint-" + i++);
			endpoint.setDestination(queueInformation);
			endpoint.setMessageListener(message -> {
				new GerenciadorFila().process(message);
			});
			registrar.registerEndpoint(endpoint);

		}

	}
}
