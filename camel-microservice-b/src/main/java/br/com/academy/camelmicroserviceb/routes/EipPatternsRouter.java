package br.com.academy.camelmicroserviceb.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouter extends RouteBuilder {
	
	@Override
	public void configure() throws Exception {
		
		from("timer:multicast?period=15000")
		.to("direct:test-direct")
		.to("direct:test-direct-2");
		
		from("direct:test-direct")
		.log("Olha o Log");
		
		from("direct:test-direct-2")
		.routeId("direct-2")
		.log("Olha o Log DOIS");
	}
}
