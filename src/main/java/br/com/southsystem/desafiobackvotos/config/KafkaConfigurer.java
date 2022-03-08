package br.com.southsystem.desafiobackvotos.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfigurer {
    @Value("${topic.votacao}")
    private String topic;

    @Bean
    public NewTopic topicExample() {
        return TopicBuilder.name(topic)
                .partitions(5)
                .build();
    }
}