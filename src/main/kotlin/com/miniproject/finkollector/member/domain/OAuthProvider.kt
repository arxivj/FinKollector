package com.miniproject.finkollector.member.domain

enum class OAuthProvider(val description: String) {
    NONE("일반유저"), // 일반유저 - 일반 이메일 로그인
    GOOGLE("구글"),   // 구글
    KAKAO("카카오"),   // 카카오
    NAVER("네이버");   // 네이버

    override fun toString(): String = description
}
