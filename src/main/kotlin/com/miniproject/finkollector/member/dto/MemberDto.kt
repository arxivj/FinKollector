package com.miniproject.finkollector.member.dto

/**
 * 회원 가입 요청을 위한 DTO.
 *
 * @property email 회원의 이메일 주소.
 * @property password 회원의 비밀번호.
 */
data class JoinRequest(
    val email: String,
    val password: String
)

/**
 * 회원 가입 초기화 응답을 위한 DTO.
 *
 * @property token 회원 가입을 위한 인증 토큰.
 */
data class JoinResponse(val token: String)

/**
 * 이메일 인증 요청을 위한 DTO.
 *
 * @property email 인증을 요청하는 회원의 이메일 주소.
 * @property token 인증 토큰.
 */
data class VerifyRequest(
    val email: String,
    val token: String
)

/**
 * 이메일 인증 응답을 위한 DTO.
 *
 * @property success 인증 성공 여부.
 */
data class VerifyResponse(val success: Boolean)

/**
 * 회원 가입 완료 요청을 위한 DTO.
 *
 * @property email 가입을 완료하려는 회원의 이메일 주소.
 * @property password 회원의 비밀번호.
 * @property code 이메일 인증 코드.
 */
data class CompleteJoinRequest(
    val email: String,
    val password: String,
    val code: String
)
