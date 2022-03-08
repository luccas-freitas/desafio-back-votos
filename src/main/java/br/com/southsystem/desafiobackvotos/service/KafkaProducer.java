package br.com.southsystem.desafiobackvotos.service;

import org.springframework.messaging.handler.annotation.Payload;

public interface KafkaProducer {
    void send(@Payload Object message);
}
