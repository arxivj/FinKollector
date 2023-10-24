package com.miniproject.finkollector.stock.service

import com.miniproject.finkollector.stock.domain.IexEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.text.SimpleDateFormat
import java.util.*

@Service
class IexService(
    @Value("\${iex.token}") private val token: String,
    @Value("\${iex.endpoint}") private val endPoint: String
) {

    private val restTemplate = RestTemplate()
    private val dateFormat = SimpleDateFormat("yyyyMMdd")

    fun getStockData(ticker: String, startDate: Date, endDate: Date): List<IexEntity> {
        val range = determineRange(startDate, endDate)
        val url = buildUrl(ticker, range, endDate)

        val response: ResponseEntity<Array<IexEntity>> = restTemplate.getForEntity(url, Array<IexEntity>::class.java)
        return response.body?.toList() ?: emptyList()
    }

    private fun buildUrl(ticker: String, range: String, endDate: Date): String {
        if (range == "date") {
            return "$endPoint/stock/$ticker/chart/$range/${dateFormat.format(endDate)}?token=$token"
        } else {
            return "$endPoint/stock/$ticker/chart/$range?token=$token"
        }
    }

    private fun determineRange(startDate: Date, endDate: Date): String {
        val diffDays = daysBetween(startDate, endDate)

        return when {
            diffDays <= 1 -> "dynamic"
            diffDays <= 5 -> "5d"
            diffDays <= 30 -> "1m"
            diffDays <= 90 -> "3m"
            diffDays <= 180 -> "6m"
            diffDays <= 365 -> "1y"
            diffDays <= 730 -> "2y"
            diffDays <= 1825 -> "5y"
            else -> "max"
        }
    }
    private fun daysBetween(d1: Date, d2: Date): Int {
        val diff = d2.time - d1.time
        return (diff / (24 * 60 * 60 * 1000)).toInt()
    }
}
