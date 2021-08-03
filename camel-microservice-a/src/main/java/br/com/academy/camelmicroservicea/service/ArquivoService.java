package br.com.academy.camelmicroservicea.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.academy.camelmicroservicea.mapper.ArquivoMapper;
import br.com.academy.camelmicroservicea.message.ArquivoProducer;
import br.com.academy.camelmicroservicea.model.ArquivoModel;
import br.com.academy.camelmicroservicea.repository.ArquivoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArquivoService {

	private static final String AGUARDANDO_PROCESSAMENTO = "AGUARDANDO_PROCESSAMENTO";

	@Autowired
	private ArquivoRepository repository;
	
	@Autowired
	private ArquivoProducer producer;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	@Value("${activemq.arquivos.solicitatos.destination}")
    private String destination;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public ArquivoModel save(ArquivoModel model) {
			
		try {
			ArquivoModel arquivoModel = ArquivoMapper.toModel(repository.save(ArquivoMapper.toEntity(model)));

			if (AGUARDANDO_PROCESSAMENTO.equalsIgnoreCase(arquivoModel.getStatus())) {
				
				producer.sendTo(destination, arquivoModel);
				
				kafkaProducerService.send(mapper.writeValueAsString(arquivoModel));
			}
			return arquivoModel;
		} catch (JsonProcessingException e) {
			log.debug(e.getMessage());
			throw new RuntimeException();
		}
	}

	public List<ArquivoModel> findAll() {
		return repository.findAll().stream().map(ArquivoMapper::toModel).collect(Collectors.toList());
	}
}
