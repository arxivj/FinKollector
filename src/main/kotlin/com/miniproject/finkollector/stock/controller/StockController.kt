package com.miniproject.finkollector.stock.controller

import com.miniproject.finkollector.stock.service.StockService
import com.miniproject.finkollector.stock.dto.RecommendedStockDto
import com.miniproject.finkollector.stock.dto.StockRequestDto
import com.miniproject.finkollector.stock.domain.StockEntity
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

        val thumbImg = mapOf(
            "AAPL" to "https://i.namu.wiki/i/d160Dqec9tDdXjSJhO_QWYqUp2DHO2B-aKvtCRxjdMwxoqSad8McPkKWnBCuWutazN79tv_w6yNZqgTZ_RBElg.svg",
            "MSFT" to "https://upload.wikimedia.org/wikipedia/commons/9/96/Microsoft_logo_%282012%29.svg",
            "GOOG" to "https://i.namu.wiki/i/vTLMLFxibstAL3kvqchcO1H8QENk60NlxGKs1wOStFqP3-fEqxBsrcNNteCYKN0S0IteYfjiT2POo9u4zFLRLA.svg",
            "AMZN" to "https://upload.wikimedia.org/wikipedia/commons/4/4a/Amazon_icon.svg",
            "MRK" to "https://www.msd-korea.com/wp-content/themes/mhh-mhh2-mcc-theme/images/msd-logo.svg"
        )


        return listOf(
            RecommendedStockDto("AAPL", "Apple Inc.", 150.00, 2.5, thumbImg["AAPL"] ?: ""),
            RecommendedStockDto("MSFT", "Microsoft Corp.", 250.00, 1.2, thumbImg["MSFT"] ?: ""),
            RecommendedStockDto("GOOG", "Alphabet Inc.", 2800.00, -0.8, thumbImg["GOOG"] ?: ""),
            RecommendedStockDto("AMZN", "Amazon.com Inc.", 3400.00, 3.0, thumbImg["AMZN"] ?: ""),
            RecommendedStockDto("MRK", "MRK Inc.", 650.00, 4.5, thumbImg["MRK"] ?: "")
        )
    }

}