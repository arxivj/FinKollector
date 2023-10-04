package com.miniproject.finkollector.domain.kafka.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

data class StockEntity(


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,



)