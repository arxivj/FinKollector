package com.miniproject.finkollector.stock.service

import com.miniproject.finkollector.stock.domain.StockEntity
import com.miniproject.finkollector.stock.dto.StockRequestDto
import com.miniproject.finkollector.stock.repository.StockRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class StockService(private val stockRepository: StockRepository) {

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

}