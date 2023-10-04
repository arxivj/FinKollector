package com.miniproject.finkollector.domain.kafka.producer

import com.miniproject.finkollector.domain.kafka.ApiProperties
import com.miniproject.finkollector.domain.kafka.KafkaProperties
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service // 외부 API에 주기적으로 시세 데이터를 요청 후 받아와서 topic 발행
class ProducerService(
    private val restTemplate: RestTemplate,
    var kafkaTemplate: KafkaTemplate<String, String>,
    val kafkaProperties: KafkaProperties,
    val apiProperties: ApiProperties
) {
        private val logger = LoggerFactory.getLogger(ProducerService::class.java)
    @PostConstruct
    fun init() {
        fetchStockData()
    }

    @Scheduled(cron = "*/5 * * * * ?")
    fun fetchStockData() {
        try {
            val response: ResponseEntity<String> = restTemplate.getForEntity(apiProperties.url, String::class.java)
            val stockData = response.body

            // 위 stockData로 topic 발행
            stockData?.let {
                publishTopic(it)
            } ?: logger.warn("Received null stock data from API")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun publishTopic(data: String) {
        kafkaTemplate.send(kafkaProperties.name, data)
        logger.info("Data sent to topic $data")
    }

}