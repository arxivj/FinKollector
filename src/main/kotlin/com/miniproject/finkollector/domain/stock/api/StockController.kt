package com.miniproject.finkollector.domain.stock.api

import com.miniproject.finkollector.domain.stock.domain.StockService
import com.miniproject.finkollector.domain.stock.dto.StockRequestDto
import com.miniproject.finkollector.domain.stock.entity.StockEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stock")
class StockController(private val stockService: StockService) {
    @PostMapping("/chart")
    fun getStocksByTickerAndDate(@RequestBody request: StockRequestDto): List<StockEntity> {
        return stockService.getStockDataByTickerAndDateRange(request)
    }

    @GetMapping("/mini-chart")
    fun fetchMiniChartStockData(@RequestParam ticker: String): List<StockEntity> {
        val response = stockService.fetchMiniChartStockData(ticker)
        println("응답 형태 확인: $response")
        return response
    }

}