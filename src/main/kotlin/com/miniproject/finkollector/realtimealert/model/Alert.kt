package com.miniproject.finkollector.realtimealert.model

data class Alert(
    var userId: Long,
    var stockSymbol: String,
    var alertThreshold: Double,
    var direction: String
)