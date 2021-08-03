package br.com.academy.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ArquivoRoute extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		from("activemq:arquivos-solicitados-queue")
		.to("activemq:arquivos-em-processamento-queue");
	}

}
