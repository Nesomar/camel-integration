package br.com.academy.camelmicroservicea.router;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public abstract class BasicRouter extends RouteBuilder {

	public abstract void executeAsyncProcess(String key, Exchange exchange);
	
	public String[] configureProcess(List<String> targets) {
		
		List<String> calls = new ArrayList<>();
		
		targets.forEach(target -> {
			
			calls.add("direct:" + target);
			
			from("direct:" + target)
				.process(exchange -> executeAsyncProcess(target, exchange));
		});
		
		return calls.toArray(new String[calls.size()]);
		
	}
}
