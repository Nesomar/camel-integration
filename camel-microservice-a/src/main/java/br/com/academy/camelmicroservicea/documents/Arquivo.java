package br.com.academy.camelmicroservicea.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "arquivo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arquivo {
	
	private String id;
	private String data;
	private String nome;
	private String conteudo;
	
}
