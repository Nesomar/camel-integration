package br.com.academy.camelmicroservicea.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.academy.camelmicroservicea.mapper.ArquivoMapper;
import br.com.academy.camelmicroservicea.message.ArquivoProducer;
import br.com.academy.camelmicroservicea.model.ArquivoModel;
import br.com.academy.camelmicroservicea.repository.ArquivoRepository;

@Service
public class ArquivoService {

	@Autowired
	private ArquivoRepository repository;
	
	@Autowired
	private ArquivoProducer producer;
	
	@Value("${activemq.arquivos.solicitatos.destination}")
    private String destination;
	
	public ArquivoModel save(ArquivoModel model) {
		
		ArquivoModel arquivoModel = ArquivoMapper.toModel(repository.save(ArquivoMapper.toEntity(model)));
		
		if("AGUARDANDO_PROCESSAMENTO".equalsIgnoreCase(arquivoModel.getStatus())) {
			producer.sendTo(destination, arquivoModel);
		}
		
		return arquivoModel;
	}

	public List<ArquivoModel> findAll() {
		return repository.findAll().stream().map(ArquivoMapper::toModel).collect(Collectors.toList());
	}
}
