package br.com.academy.camelmicroservicea.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.camelmicroservicea.mapper.ArquivoMapper;
import br.com.academy.camelmicroservicea.model.ArquivoModel;
import br.com.academy.camelmicroservicea.repository.ArquivoRepository;

@Service
public class ArquivoService {

	@Autowired
	private ArquivoRepository repository;

	public ArquivoModel save(ArquivoModel model) {
		return ArquivoMapper.toModel(repository.save(ArquivoMapper.toEntity(model)));
	}

	public List<ArquivoModel> findAll() {
		return repository.findAll().stream().map(ArquivoMapper::toModel).collect(Collectors.toList());
	}
}
