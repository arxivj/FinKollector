package com.miniproject.finkollector.member.domain

enum class OAuthProvider { //TODO: OAuth 구현 후 패키지 이동 필요
    NONE,    // 일반유저 - 일반 이메일 로그인
    GOOGLE,  // 구글
    KAKAO,   // 카카오
    NAVER;   // 네이버

    override fun toString(): String {
        return when(this) {
            NONE -> "일반유저"
            GOOGLE -> "구글"
            KAKAO -> "카카오"
            NAVER -> "네이버"
        }
    }
}
