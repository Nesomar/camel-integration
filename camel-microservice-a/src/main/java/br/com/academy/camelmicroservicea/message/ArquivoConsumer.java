package br.com.academy.camelmicroservicea.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.academy.camelmicroservicea.model.ArquivoModel;
import br.com.academy.camelmicroservicea.service.ArquivoService;

@Component
public class ArquivoConsumer {
	
	@Autowired
	private ArquivoService service;

	private ObjectMapper mapper = new ObjectMapper();
	
	@JmsListener(destination = "${activemq.arquivos.processados.destination}", containerFactory = "jmsFactory")
	public void processToDo(String message) {
		
		try {
			var model = mapper.readValue(message, ArquivoModel.class);
			
			if ("EM_PROCESSAMENTO".equalsIgnoreCase(model.getStatus())) {
				model.setStatus("PROCESSADO");
			}
			
			service.save(model);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
