package com.miniproject.finkollector.member.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class EmailCodeStore(
    private val redisTemplate: StringRedisTemplate,
    private val emailCodeExpirationTime: Long
) {
    // 주어진 이메일에 대해 코드를 Redis에 저장하고 시간을 설정
    fun saveEmailCode(email: String, code: String) {
        //opsForValue: Strings를 쉽게 Serialize / Deserialize 해주는 Interface
        redisTemplate.opsForValue().set(email, code, emailCodeExpirationTime, TimeUnit.MINUTES)
    }

    // 제공된 코드가 Redis에 저장된 코드와 일치하는지 확인
    //.?let 람다함수, ?: 같은 엘비스 연산자를 활용하여 null 안정성 보장하는 목적
    fun isValidEmailCode(email: String, providedCode: String): Boolean =
        redisTemplate.opsForValue().get(email)?.let { storedCode ->
            storedCode == providedCode
        } ?: false
}
