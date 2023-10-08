package com.miniproject.finkollector.domain.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.miniproject.finkollector.domain.kafka.entity.StockEntity
import com.miniproject.finkollector.domain.kafka.entity.StockRepository
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class StockConsumer(private val stockRepository: StockRepository) {

    companion object {
        private val logger = LoggerFactory.getLogger(StockConsumer::class.java)
    }

    @KafkaListener(topics = ["stock-list"], groupId = "stock-data")
    fun consume(message: String) {
        logger.info("Message received $message")
        // JSON 메시지를 StockEntity 객체로 변환
        val stockList = ObjectMapper().readValue(message, Array<StockEntity>::class.java).toList()

        // 데이터베이스에 저장
        stockRepository.saveAll(stockList)
    }


}