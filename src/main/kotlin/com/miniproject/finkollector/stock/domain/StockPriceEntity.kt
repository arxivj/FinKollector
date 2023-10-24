package com.miniproject.finkollector.stock.domain

import jakarta.persistence.*

@Entity
@Table(name = "stockprice")
data class StockPriceEntity(

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val date: String,

    val ticker: String,

    val open: Double,

    @Column(name = "closing")
    val close: Double,

    @Column(name = "highest")
    val high: Double,

    @Column(name = "lowest")
    val low: Double,

    @Column(name = "upt_open")
    val uptOpen: Double,

    @Column(name = "upt_closing")
    val uptClose: Double,

    @Column(name = "upt_highest")
    val uptHigh: Double,

    @Column(name = "upt_lowest")
    val uptLow: Double,

    val volume: Int

)
