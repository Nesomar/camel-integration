package br.com.academy.camelmicroservicea.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArquivoModel {

	private String id;
	private String data;
	private String nome;
	private String conteudo;
	private String status;
}
