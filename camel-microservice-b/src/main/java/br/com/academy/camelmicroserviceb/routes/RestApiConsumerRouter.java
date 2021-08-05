package br.com.academy.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestApiConsumerRouter extends RouteBuilder {

	@Value("${integration.port}")
	private Integer port;
	
	@Value("${integration.host}")
	private String host;
	
	@Override
	public void configure() throws Exception {
		
		restConfiguration().host(host).port(port);
		
		from("timer:rest-api-consumer?period=10000")
		.to("rest:get:/arquivos")
		.log("${body}");
	}

}
