package br.com.academy.camelmicroserviceb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArquivoModel {

	private String id;
	private String data;
	private String nome;
	private String conteudo;
	private String status;
	
}
