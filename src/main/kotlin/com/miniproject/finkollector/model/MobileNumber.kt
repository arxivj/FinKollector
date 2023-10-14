package com.miniproject.finkollector.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Column
import javax.validation.constraints.NotEmpty

@Embeddable
data class MobileNumber(
    @field:NotEmpty
    @field:Column(name = "country_code", length = 5)
    val countryCode: String,

    @field:NotEmpty
    @field:Column(name = "phone_number", length = 15)
    val phoneNumber: String,

    @field:Column(name = "is_verified", nullable = false)
    val isVerified: Boolean = false
)
