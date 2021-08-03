package br.com.academy.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ArquivoKafkaRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("kafka:topico.arquivo.solicitado")
		.to("kafka:topico.arquivo.processado");
	}

}
