package com.miniproject.finkollector.domain.stock.dto

data class StockRequestDto(
    val ticker: String,
    val startDate: String,
    val endDate: String
)