package br.com.academy.camelmicroservicea.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${topic.name.arquivo.solicitado}")
	private String topic;

	@Override
	public void send(String message) {
	   log.info("Payload enviado: {}", message);
       kafkaTemplate.send(topic, message);
	}

}
