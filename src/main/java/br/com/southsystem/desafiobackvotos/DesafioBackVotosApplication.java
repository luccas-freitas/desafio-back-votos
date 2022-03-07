package br.com.southsystem.desafiobackvotos;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableRabbit
@EnableAsync
@SpringBootApplication
public class DesafioBackVotosApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesafioBackVotosApplication.class, args);
	}
}
