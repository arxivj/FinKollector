package com.miniproject.finkollector.stock.dto

data class StockRequestDto(
    val ticker: String,
    val startDate: String,
    val endDate: String
)