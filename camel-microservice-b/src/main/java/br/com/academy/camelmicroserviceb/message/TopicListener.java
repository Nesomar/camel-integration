package br.com.academy.camelmicroserviceb.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicListener {

	@Value(value = "${topic.name.arquivo.processado}")
	private String userTopicName;

	@KafkaListener(topics = "${topic.name.arquivo.processado}")
	public void consumeArquivosSolicitados(ConsumerRecord<String, String> payload) {
		log.info("Tópico Arquivo Solicitado: {}", userTopicName);
		log.info("Message Solicitado: {}", payload.value());
	}
}
