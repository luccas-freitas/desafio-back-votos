package br.com.southsystem.desafiobackvotos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableKafka
@EnableAsync
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Desafio South System",
        version = "0.0.1-SNAPSHOT",
        description = "Votação de Pautas"))
@SpringBootApplication
public class DesafioBackVotosApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesafioBackVotosApplication.class, args);
    }
}
