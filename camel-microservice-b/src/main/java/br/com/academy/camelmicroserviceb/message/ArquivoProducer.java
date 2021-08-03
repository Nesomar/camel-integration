package br.com.academy.camelmicroserviceb.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class ArquivoProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendTo(String destination, String model) {
		jmsTemplate.convertAndSend(destination, model);
	}
}
