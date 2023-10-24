package com.miniproject.finkollector.stock.dto

import java.util.Date

data class IexRequestDto(
    val ticker: String,
    val startDate: Date,
    val endDate: Date
)
