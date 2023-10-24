package com.miniproject.finkollector.stock.domain

import java.util.Date

data class IexEntity(

    val id: String,

    val symbol: String,

    val open: Double,

    val high: Double,

    val low: Double,

    val close: Double,

    val priceDate: Date,

    val volume: Long,

    val uOpen: Double,

    val uClose: Double,

    val uHigh: Double,

    val uLow: Double,

    val uVolume: Long,

    val fOpen: Double,

    val fClose: Double,

    val fHigh: Double,

    val fLow: Double,

    val fVolume: Long,

    val label: String,

    val change: Double,

    val changePercent: Double

)
