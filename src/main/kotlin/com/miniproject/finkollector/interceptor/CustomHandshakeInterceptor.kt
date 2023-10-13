package com.miniproject.finkollector.interceptor

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor

class CustomHandshakeInterceptor : HandshakeInterceptor {

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        // 헤더에서 JWT access token을 가져와 검증
        val token = request.headers.getFirst("Authorization") ?: return false

        // JWT 토큰으로 검증?
        if (!isValidToken(token)) {
//            response.statusCode = FORBIDDEN
            return false
        }
        return true
    }

    private fun isValidToken(token: String): Boolean {
        // JWT 토큰에서 서명 or 만료시간? 같은걸 확인해서 실제 토큰 검증 로직 구현 예정
        return true
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {}

}