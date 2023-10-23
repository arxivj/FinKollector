package com.miniproject.finkollector.stock.repository

import com.miniproject.finkollector.stock.domain.StockEntity
import com.miniproject.finkollector.stock.domain.StockPriceEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StockPriceRepository: JpaRepository<StockPriceEntity, Long> {
    fun findByTickerAndDateBetween(ticker: String, startDate: String, endDate: String): List<StockPriceEntity>
}