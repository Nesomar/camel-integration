package br.com.academy.camelmicroserviceb.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.academy.camelmicroserviceb.model.ArquivoModel;

@Component
public class ArquivoConsumer {

	@Autowired
	private ArquivoProducer producer;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Value("${activemq.arquivos.processados.destination}")
    private String destination;
	
	@JmsListener(destination = "${activemq.arquivos.em-processamento.destination}", containerFactory = "jmsFactory")
	public void processArquivo(String message) {
		
		try {
			var model = mapper.readValue(message, ArquivoModel.class);
			
			model.setStatus("EM_PROCESSAMENTO");
			
			producer.sendTo(destination, mapper.writeValueAsString(model));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
