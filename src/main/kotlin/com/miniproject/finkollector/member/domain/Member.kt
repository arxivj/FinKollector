package com.miniproject.finkollector.member.domain

import com.miniproject.finkollector.model.Email
import com.miniproject.finkollector.model.MobileNumber
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(

    // 회원의 고유 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    val id: Long? = null,

    // 회원의 이메일 정보 (Email 객체로 임베디드됨)
    @Embedded
    @AttributeOverride(
        name = "value",
        column = Column(name = "email", nullable = false, unique = true, updatable = false, length = 50)
    )
    val email: Email,

    // 비밀번호 (해시 처리됨)
    @Column(nullable = false)
    var password: String,

    // 비밀번호 해싱에 사용된 솔트
    @Column(nullable = false)
    val salt: String,

    // 회원의 휴대폰 정보 (MobileNumber 객체로 임베디드됨)
    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "countryCode", column = Column(name = "country_code", nullable = false, length = 5)),
        AttributeOverride(name = "phoneNumber", column = Column(name = "phone_number", nullable = false, length = 15)),
        AttributeOverride(name = "isVerified", column = Column(name = "phone_verified", nullable = false))
    )
    var mobile: MobileNumber? = null,

    // 회원의 역할 (관리자, 사용자 등)
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: MutableSet<Role> = hashSetOf(),

    // OAuth 제공자 정보 (Google, Kakao, Naver 등)
    @Enumerated(EnumType.STRING)
    val oauthClient: OAuthProvider? = null
)
