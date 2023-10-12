package com.miniproject.finkollector.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaConfig {
    @Bean
    fun topic(): NewTopic {
        return TopicBuilder.name("stock-list").build()
    }
}