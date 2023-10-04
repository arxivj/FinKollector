package com.miniproject.finkollector.domain.kafka.api

import com.miniproject.finkollector.domain.kafka.entity.StockEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


//@RestController
//@RequestMapping("/kafka")
//class StockController(var stockService: StockService) {
//
//    //Client가 요청시 DB에서 list를 꺼내서 응답해주는 메서드
//    @GetMapping("/stocks")
//    fun stocks(): ResponseEntity<List<StockEntity>> {
//        val list = stockService.stockList();
//        return ResponseEntity.ok(list)
//    }
//
//
//}