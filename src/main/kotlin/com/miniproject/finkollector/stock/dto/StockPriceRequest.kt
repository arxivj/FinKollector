package com.miniproject.finkollector.stock.dto

data class StockPriceRequest (
    val ticker: String,
    val startDate: String,
    val endDate: String
)