package com.miniproject.finkollector.stock.repository

import com.miniproject.finkollector.stock.domain.StockEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<StockEntity, Long> {
    fun findByTickerAndDateBetween(ticker: String, startDate: String, endDate: String): List<StockEntity>
}