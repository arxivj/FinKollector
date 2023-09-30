package com.miniproject.finkollector.domain.member.domain

import com.miniproject.finkollector.domain.model.Email
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null,

    @Embedded
    @AttributeOverride(
        name = "value",
        column = Column(name = "email", nullable = false, unique = true, updatable = false, length = 50)
    )
    var email: Email,

    var password: String,

    var salt: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = hashSetOf(),

    @Enumerated(EnumType.STRING)
    var oauthClient: OAuthProvider? = null,

    )