package br.com.southsystem.desafiobackvotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableKafka
@EnableAsync
@EnableFeignClients
@SpringBootApplication
public class DesafioBackVotosApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesafioBackVotosApplication.class, args);
	}
}
