package com.miniproject.finkollector.domain.stock.entity

import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository: JpaRepository<StockEntity, Long> {
    fun findByTickerAndDateBetween(ticker: String, startDate: String, endDate: String): List<StockEntity>
}