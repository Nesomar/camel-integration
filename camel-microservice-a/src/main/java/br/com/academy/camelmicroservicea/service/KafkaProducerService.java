package br.com.academy.camelmicroservicea.service;

public interface KafkaProducerService {

	void send(String message);
}
