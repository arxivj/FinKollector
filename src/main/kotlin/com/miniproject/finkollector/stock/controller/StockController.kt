package com.miniproject.finkollector.stock.controller

import com.miniproject.finkollector.stock.service.StockService
import com.miniproject.finkollector.stock.service.PredictionService
import com.miniproject.finkollector.stock.dto.RecommendedStockDto
import com.miniproject.finkollector.stock.dto.StockRequestDto
import com.miniproject.finkollector.stock.domain.StockEntity
import com.miniproject.finkollector.stock.dto.Prediction.PredictionRequest
import com.miniproject.finkollector.stock.dto.Prediction.PredictionResponse
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/stock")
class StockController(private val stockService: StockService, private val predictionService: PredictionService) {
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

    /** 주식 예측 서비스 */
    @PostMapping("/predict")
    fun predictStockPrice(@RequestBody request: PredictionRequest): PredictionResponse {
        // AI한테 예측값을 받아오는 서비스로직
        return predictionService.getPrediction(request)
    }


    @GetMapping("/company-info")
    fun getCompanyInfo(): List<Map<String, Any>> {
        return listOf(
            mapOf(
                "ticker" to "AAPL",
                "company_nm" to "Apple Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Tech Giant",
                "sector" to "Technology",
                "tags" to "Tech, iPhone, Mac",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "MSFT",
                "company_nm" to "Microsoft Corporation",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Software Giant",
                "sector" to "Technology",
                "tags" to "Software, Windows, Azure",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "GOOGL",
                "company_nm" to "Alphabet Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Internet Services",
                "sector" to "Technology",
                "tags" to "Search, Ads, Cloud",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "AMZN",
                "company_nm" to "Amazon Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "E-commerce & Cloud Computing",
                "sector" to "Consumer Discretionary",
                "tags" to "E-commerce, AWS, Prime",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "TSLA",
                "company_nm" to "Tesla Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Electric Vehicle Manufacturer",
                "sector" to "Consumer Discretionary",
                "tags" to "EV, Solar, Battery",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "FB",
                "company_nm" to "Facebook Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Social Media Platform",
                "sector" to "Technology",
                "tags" to "Social, Ads, Oculus",
                "cntry" to "USA"
            ),
            mapOf(
                "ticker" to "NFLX",
                "company_nm" to "Netflix Inc.",
                "stock_exchange_nm" to "NASDAQ",
                "stock_desc" to "Streaming Service",
                "sector" to "Communication Services",
                "tags" to "Streaming, Originals, Series",
                "cntry" to "USA"
            ),
        )
    }

    @GetMapping("/stock-exchange")
    fun getStockExchange(): List<Map<String, Any>> {
        return listOf(
            mapOf(
                "exchange_cd" to "NASDAQ",
                "exchange_nm" to "NASDAQ Stock Market",
                "area" to "North America",
                "exchange_desc" to "2nd largest exchange by market capitalization",
                "ind" to 1001
            ),
            mapOf(
                "exchange_cd" to "NYSE",
                "exchange_nm" to "New York Stock Exchange",
                "area" to "North America",
                "exchange_desc" to "Largest stock exchange by market capitalization",
                "ind" to 1002
            ),
            mapOf(
                "exchange_cd" to "LSE",
                "exchange_nm" to "London Stock Exchange",
                "area" to "Europe",
                "exchange_desc" to "Leading stock exchange in Europe",
                "ind" to 1003
            ),
            mapOf(
                "exchange_cd" to "TSE",
                "exchange_nm" to "Tokyo Stock Exchange",
                "area" to "Asia",
                "exchange_desc" to "Largest stock exchange in Asia by market capitalization",
                "ind" to 1004
            ),
            mapOf(
                "exchange_cd" to "HKEX",
                "exchange_nm" to "Hong Kong Stock Exchange",
                "area" to "Asia",
                "exchange_desc" to "One of the top global exchanges by market capitalization",
                "ind" to 1005
            ),
            mapOf(
                "exchange_cd" to "SSE",
                "exchange_nm" to "Shanghai Stock Exchange",
                "area" to "Asia",
                "exchange_desc" to "Major stock exchange in China",
                "ind" to 1006
            ),
            mapOf(
                "exchange_cd" to "BSE",
                "exchange_nm" to "Bombay Stock Exchange",
                "area" to "Asia",
                "exchange_desc" to "Oldest stock exchange in Asia",
                "ind" to 1007
            ),
            mapOf(
                "exchange_cd" to "ASX",
                "exchange_nm" to "Australian Securities Exchange",
                "area" to "Oceania",
                "exchange_desc" to "Largest stock exchange in Australia",
                "ind" to 1008
            ),
            mapOf(
                "exchange_cd" to "JSE",
                "exchange_nm" to "Johannesburg Stock Exchange",
                "area" to "Africa",
                "exchange_desc" to "Largest stock exchange in Africa",
                "ind" to 1009
            ),
        )
    }


    @GetMapping("/stock-price")
    fun getStockPrice(): List<Map<String, Any>> {
        return listOf(
            mapOf(
                "date" to "2023-10-23",
                "ticker" to "AAPL",
                "closing" to 150,
                "highest" to 155,
                "open" to 149,
                "lowest" to 148,
                "upt_closing" to 149,
                "upt_open" to 148,
                "upt_highest" to 154,
                "upt_lowest" to 147,
                "volume" to 1000000L
            ),
            mapOf(
                "date" to "2023-10-23",
                "ticker" to "MSFT",
                "closing" to 250,
                "highest" to 255,
                "open" to 248,
                "lowest" to 247,
                "upt_closing" to 249,
                "upt_open" to 247,
                "upt_highest" to 253,
                "upt_lowest" to 246,
                "volume" to 800000L
            )
        )
    }




}