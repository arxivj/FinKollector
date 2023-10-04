package com.miniproject.finkollector.domain.kafka.domain

import com.miniproject.finkollector.domain.kafka.entity.StockEntity
import com.miniproject.finkollector.domain.kafka.entity.StockRepository
import org.springframework.stereotype.Service

@Service
class StockService(val stockRepository: StockRepository) {

    // DB에서 주식 시세정보 리스트를 꺼내온다
    fun stockList(): List<StockEntity> {
        return stockRepository.findAll();
    }


}