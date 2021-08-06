package br.com.academy.camelmicroservicea.router;

import java.util.Arrays;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ActiveMqRouter extends BasicRouter {

	@Override
	public void executeAsyncProcess(String key, Exchange exchange) {
		log.info("executeAsyncProcess ---> by Key:" + key);
		
	}

	@Override
	public void configure() throws Exception {
		
		String[] routes = configureProcess(Arrays.asList("sqs", "sns"));
		
		from("timer:testeAsync?timer=3500")
		.multicast().parallelProcessing(true).timeout(60000).to(routes)
		.end()
		.to("direct:log-test");
		
		from("direct:log-test")
		.log("Chegou no fim");
		
	}

}
