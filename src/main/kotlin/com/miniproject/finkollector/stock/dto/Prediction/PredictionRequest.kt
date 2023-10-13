package com.miniproject.finkollector.stock.dto.Prediction

import java.time.LocalDate

data class PredictionRequest(
    val stockSymbol: String,
    val dateRange: DateRange
)

data class DateRange(
    val startDate: LocalDate,
    val endDate: LocalDate
)