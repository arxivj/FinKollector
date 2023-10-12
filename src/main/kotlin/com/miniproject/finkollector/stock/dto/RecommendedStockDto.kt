package com.miniproject.finkollector.stock.dto

data class RecommendedStockDto(
    val ticker: String,
    val companyName: String,
    val currentPrice: Double,
    val change: Double,
    val thumbnailUrl: String
)
