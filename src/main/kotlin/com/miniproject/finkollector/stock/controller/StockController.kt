package com.miniproject.finkollector.stock.controller

import com.miniproject.finkollector.stock.service.StockService
import com.miniproject.finkollector.stock.service.PredictionService
import com.miniproject.finkollector.stock.dto.RecommendedStockDto
import com.miniproject.finkollector.stock.dto.StockRequestDto
import com.miniproject.finkollector.stock.domain.StockEntity
import com.miniproject.finkollector.stock.dto.Prediction.PredictionRequest
import com.miniproject.finkollector.stock.dto.Prediction.PredictionResponse
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@RestController
@RequestMapping("/stock")
class StockController(private val stockService: StockService, private val predictionService: PredictionService) {

    @PostMapping("/chart")
    @Operation(
        summary = "[주식 데이터 조회]",
        description = "주어진 기간 동안의 특정 주식 데이터를 조회",
        responses = [
            ApiResponse(responseCode = "200", description = "조회 성공",
                content = [Content(schema = Schema(implementation = StockEntity::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun getStocksByTickerAndDate(@RequestBody request: StockRequestDto): List<StockEntity> {
        return stockService.getStockDataByTickerAndDateRange(request)
    }

    @GetMapping("/mini-chart")
    @Operation(
        summary = "[미니 차트 그리기용 특정 주식 데이터 조회]",
        description = "특정 ticker에 대한 주식 데이터를 조회",
        responses = [
            ApiResponse(responseCode = "200", description = "조회 성공",
                content = [Content(schema = Schema(implementation = StockEntity::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun fetchMiniChartStockData(@RequestParam ticker: String): List<StockEntity> {
        val response = stockService.fetchMiniChartStockData(ticker)
        println("응답 형태 확인: $response")
        return response
    }

    @GetMapping("/top5")
    @Operation(
        summary = "[Ranking TOP 5 조회]",
        description = "[추천 주식 TOP 5를 조회]: 더미 데이터이며 임시로 사용 중",
        responses = [
            ApiResponse(responseCode = "200", description = "조회 성공",
                content = [Content(schema = Schema(implementation = RecommendedStockDto::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
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

    @PostMapping("/predict")
    @Operation(
        summary = "[주식 가격 예측]",
        description = "[미구현]: AI 서비스를 통해 주식 가격을 예측",
        responses = [
            ApiResponse(responseCode = "200", description = "예측 성공",
                content = [Content(schema = Schema(implementation = PredictionResponse::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun predictStockPrice(@RequestBody request: PredictionRequest): PredictionResponse {
        // AI한테 예측값을 받아오는 서비스로직
        return predictionService.getPrediction(request)
    }

}