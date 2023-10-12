package com.miniproject.finkollector.stock.domain

import jakarta.persistence.*

@Entity
@Table(name = "stock")
data class StockEntity(

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val ticker: String,

    val open: Double,

    val high: Double,

    val low: Double,

    val close: Double,

    @Column(name = "adj_close")
    val adjClose: Double,

    val volume: Int,

    val date: String

)