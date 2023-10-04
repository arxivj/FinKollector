package com.miniproject.finkollector.domain.kafka

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka.topic")
class KafkaProperties {
    lateinit var name: String
}
@Component
@ConfigurationProperties(prefix = "api")
class ApiProperties {
    lateinit var url: String
}