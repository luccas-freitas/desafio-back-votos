package br.com.southsystem.desafiobackvotos.service.impl;

import br.com.southsystem.desafiobackvotos.service.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerImpl implements KafkaProducer {
    private final KafkaTemplate<String, String> template;
    private final ObjectMapper mapper;

    @Value("${topic.votacao}")
    private String topic;

    public KafkaProducerImpl(KafkaTemplate<String, String> template) {
        this.template = template;
        this.mapper = new ObjectMapper();
        this.mapper.findAndRegisterModules();
    }

    @Override
    public void send(@Payload Object payload) {
        try {
            String message = mapper.writeValueAsString(payload);
            template.send(topic, message);
        } catch (JsonProcessingException e) {
            log.error("Erro ao converter objeto: {}", payload, e);
        }
    }
}
