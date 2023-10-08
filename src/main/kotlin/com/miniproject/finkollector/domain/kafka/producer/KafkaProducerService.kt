package com.miniproject.finkollector.domain.kafka.producer

//import com.miniproject.finkollector.domain.kafka.ApiProperties
//import com.miniproject.finkollector.domain.kafka.KafkaProperties
import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Service // 외부 API에 주기적으로 시세 데이터를 요청 후 받아와서 topic 발행
class KafkaProducerService(
    private val restTemplate: RestTemplate,
    var kafkaTemplate: KafkaTemplate<String, String>,
//    val kafkaProperties: KafkaProperties,
//    val apiProperties: ApiProperties
) {
        private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)
//    @PostConstruct
//    fun init() {
//        fetchStockData()
//    }

    companion object {
        private const val API_URL = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?your_parameters_here"
    }

//    @Scheduled(cron = "*/5 * * * * ?")
//    fun fetchStockData() {
//        try {
//            val response: ResponseEntity<String> = restTemplate.getForEntity(API_URL, String::class.java)
//            val stockData = response.body
//
//            // 위 stockData로 topic 발행
//            stockData?.let {
//                publishTopic(it)
//            } ?: logger.warn("Received null stock data from API")
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    fun publishTopic(data: String) {
//        kafkaTemplate.send("stock-list",data)
//        logger.info("Data sent to topic $data")
//    }




//    @PostConstruct //DB에 카프카 토픽 저장 코드
//    fun sendAllFilesToKafka() {
//        val startDate = SimpleDateFormat("yyyy-MM-dd").parse("2012-09-04")
//        val endDate = SimpleDateFormat("yyyy-MM-dd").parse("2017-08-30")
//        var currentDate = startDate
//
//        val calendar = Calendar.getInstance()
//
//        while (!currentDate.after(endDate)) {
//            // 파일 경로 및 이름 생성
//            val filePath = "src/main/resources/stockdata/"
//            val fileName = SimpleDateFormat("yyyy-MM-dd").format(currentDate) + ".json"
//            val fullFilePath = filePath + fileName
//
//            try {
//                // JSON 파일 읽기
//                val fileContent = File(fullFilePath).readText(Charsets.UTF_8)
//
//                // Kafka에 전송
//                kafkaTemplate.send("stock-list", fileContent)
//                logger.info("Sent data to Kafka for date: $fileName")
//            } catch (e: Exception) {
//                logger.error("Error sending data for $fileName: ${e.message}")
//            }
//
//            // 날짜 증가
//            calendar.time = currentDate
//            calendar.add(Calendar.DAY_OF_MONTH, 1)
//            currentDate = calendar.time
//        }
//    }



}