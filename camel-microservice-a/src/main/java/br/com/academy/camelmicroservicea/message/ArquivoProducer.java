package br.com.academy.camelmicroservicea.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.academy.camelmicroservicea.model.ArquivoModel;

@Component
public class ArquivoProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void sendTo(String destination, ArquivoModel model) {
		jmsTemplate.convertAndSend(destination, model);
	}
}
