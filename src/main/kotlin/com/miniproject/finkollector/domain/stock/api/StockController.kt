package com.miniproject.finkollector.domain.stock.api

import com.miniproject.finkollector.domain.stock.domain.StockService
import com.miniproject.finkollector.domain.stock.dto.RecommendedStockDto
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

    @GetMapping("/top5")
    fun recommendedStocks(): List<RecommendedStockDto> {
        return listOf(
            RecommendedStockDto("AAPL", "Apple Inc.", 150.00, 2.5),
            RecommendedStockDto("MSFT", "Microsoft Corp.", 250.00, 1.2),
            RecommendedStockDto("GOOG", "Alphabet Inc.", 2800.00, -0.8),
            RecommendedStockDto("AMZN", "Amazon.com Inc.", 3400.00, 3.0),
            RecommendedStockDto("MRK", "MRK Inc.", 650.00, 4.5)
        )
    }

}