package com.miniproject.finkollector.domain.kafka.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.io.ClassPathResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.time.format.DateTimeFormatter


@RestController
@RequestMapping("/kafka")
class StockController() {

    data class Stock(
        val ticker: String,
        val open: Double,
        val high: Double,
        val low: Double,
        val close: Double,
        val adjClose: Double,
        val volume: Number,
        val date: String
    )

    @GetMapping("/create-json")
    fun createJson(){
        val objectMapper = jacksonObjectMapper()

        // 1. JSON 파일 읽기
        val resource = ClassPathResource("/stock.json")
        val stocks: List<Stock> = objectMapper.readValue(resource.inputStream)

        // 2. 날짜별로 데이터 분류
        val groupedByDate = stocks.groupBy { it.date }

        // 3. 각 날짜별로 JSON 파일 생성
        groupedByDate.forEach { (date, stocks) ->
            val jsonContent = objectMapper.writeValueAsString(stocks)
            val directory = File("stockdata")
            if (!directory.exists()) {
                directory.mkdir()
            }
            File("output-directory/${date.format(DateTimeFormatter.ISO_LOCAL_DATE)}.json").writeText(jsonContent)

        }
    }


}