package br.com.academy.camelmicroservicea.mapper;

import org.modelmapper.ModelMapper;

import br.com.academy.camelmicroservicea.documents.Arquivo;
import br.com.academy.camelmicroservicea.model.ArquivoModel;

public class ArquivoMapper {

	private static ModelMapper modelMapper = new ModelMapper();
	
	private ArquivoMapper() {
		
	}
	
	public static Arquivo toEntity(ArquivoModel model) {
		return modelMapper.map(model, Arquivo.class);
	}
	
	public static ArquivoModel toModel(Arquivo entity) {
		return modelMapper.map(entity, ArquivoModel.class);
	}
}
