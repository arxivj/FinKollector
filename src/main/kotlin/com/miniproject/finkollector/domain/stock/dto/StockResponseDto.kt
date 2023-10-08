package com.miniproject.finkollector.domain.stock.dto

data class StockResponseDto(
    val ticker: String
    // 해당 ticker의 기간만큼의 데이터이므로 배열로 전달해야 될 것 같음
)