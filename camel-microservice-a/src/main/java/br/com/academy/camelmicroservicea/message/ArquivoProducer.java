package br.com.academy.camelmicroservicea.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.academy.camelmicroservicea.model.ArquivoModel;

@Component
public class ArquivoProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public void sendTo(String destination, ArquivoModel model){
		
		try {
			jmsTemplate.convertAndSend(destination, mapper.writeValueAsString(model));
		} catch (JmsException | JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
