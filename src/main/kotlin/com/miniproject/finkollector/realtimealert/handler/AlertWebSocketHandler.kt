package com.miniproject.finkollector.realtimealert.handler

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class AlertWebSocketHandler: TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        // 1. 메시지 처리

        // 2. 알림발송 로직 ㄱㄱ
    }
}