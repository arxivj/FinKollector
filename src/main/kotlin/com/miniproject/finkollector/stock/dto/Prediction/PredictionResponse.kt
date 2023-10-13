package com.miniproject.finkollector.stock.dto.Prediction

import java.time.LocalDate

data class PredictionResponse(
    val stockSymbol: String,
    val predictedPrice: Double, //예측 주식 가격
    val confidence: Double, //예측의 신뢰도 or 정확도
    val predictionDate: LocalDate //예측 수행일
)