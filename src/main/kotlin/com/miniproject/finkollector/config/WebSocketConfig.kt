package com.miniproject.finkollector.config

import com.miniproject.finkollector.realtimealert.handler.AlertWebSocketHandler
import com.miniproject.finkollector.interceptor.CustomHandshakeInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


@Configuration
@EnableWebSocket
class WebSocketConfig : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(AlertWebSocketHandler(), "/alert")
            .setAllowedOrigins("https://localhost:8080")
            .addInterceptors(CustomHandshakeInterceptor())
    }
}