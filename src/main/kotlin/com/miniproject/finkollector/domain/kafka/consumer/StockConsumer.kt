package com.miniproject.finkollector.domain.kafka.consumer

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class StockConsumer {


    companion object {
        private val logger = LoggerFactory.getLogger(StockConsumer::class.java)
    }


    @KafkaListener(topics = ["stock-list"], groupId = "stock-data")
    fun consume(message: String) {
        logger.info("Message received $message")

    }


}