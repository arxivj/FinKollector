package com.miniproject.finkollector.realtimealert.service

import com.miniproject.finkollector.realtimealert.model.Alert
import org.springframework.stereotype.Service

@Service
class AlertService {

    private val alerts = mutableListOf<Alert>() // 실제 서비스라면 DB에서 저장된 alert을 관리하는게 좋음

    fun addAlert(alert: Alert) {
        alerts.add(alert)
    }

    fun checkStockPrice(stockSymbol: String, currentPrice: Double) {
        // 주식 가격이 사용자의 임계치?를 넘었을 경우 알림 전송 로직 추가
    }
}