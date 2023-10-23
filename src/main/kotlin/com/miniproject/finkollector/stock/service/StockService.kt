package com.miniproject.finkollector.stock.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.miniproject.finkollector.stock.domain.StockEntity
import com.miniproject.finkollector.stock.domain.StockPriceEntity
import com.miniproject.finkollector.stock.dto.StockPriceRequest
import com.miniproject.finkollector.stock.dto.StockRequestDto
import com.miniproject.finkollector.stock.repository.StockPriceRepository
import com.miniproject.finkollector.stock.repository.StockRepository
import org.springframework.stereotype.Service
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class StockService(
    private val stockRepository: StockRepository,
    private val stockPriceRepository: StockPriceRepository
) {
    fun getStockDataByTickerAndDateRange(request: StockRequestDto): List<StockEntity> {
        return stockRepository.findByTickerAndDateBetween(request.ticker, request.startDate, request.endDate)
    }

    fun fetchMiniChartStockData(ticker: String): List<StockEntity> {
//        val endDate = LocalDate.now() //원래 이 방식이 맞지만 더미데이터 이므로 마지막 기간 '2017-08-30'으로 설정
        val endDate = LocalDate.of(2017, 8, 30)
        val startDate = endDate.minusDays(30) //미니차트 기간은 한달로 하드코딩 설정

        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") //날짜 형식 변경
        val startString = startDate.format(dateFormatter)
        val endString = endDate.format(dateFormatter)

        return stockRepository.findByTickerAndDateBetween(ticker, startString, endString)
    }


    fun getStockPriceDataByTickerAndDateRange(request: StockPriceRequest): List<StockPriceEntity> {
        return stockPriceRepository.findByTickerAndDateBetween(request.ticker, request.startDate, request.endDate)
    }

    fun saveJsonToDb() {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startDate = LocalDate.parse("2022-06-01", dateFormat)
        val endDate = LocalDate.parse("2022-06-10", dateFormat)

        println("접근확인 접근확인 접근확인!!!!")

        var currentDate = startDate
        while (!currentDate.isAfter(endDate)) {
            val filePath = "/resources/dummy/${currentDate}.json"
            val jsonFile = File(filePath)
            if (jsonFile.exists()) {
                val jsonData = jsonFile.readText()
                val dataObject = ObjectMapper().readValue(jsonData, StockPriceEntity::class.java)
                stockPriceRepository.save(dataObject)
            }
            currentDate = currentDate.plusDays(1)
        }
    }


}