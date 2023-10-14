package com.miniproject.finkollector.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(EmailCodeProperties::class)
class EmailCodeConfiguration(private val properties: EmailCodeProperties) {

    @Bean
    fun emailCodeExpirationTime(): Long = properties.expirationTime
}

@ConfigurationProperties(prefix = "email.code")
class EmailCodeProperties(var expirationTime: Long = 3)