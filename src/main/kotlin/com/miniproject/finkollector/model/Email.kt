//package com.miniproject.finkollector.model
//
//import jakarta.persistence.Column
//import jakarta.persistence.Embeddable
//import javax.validation.constraints.NotEmpty
//import javax.validation.constraints.Email as EmailValidation
//
//@Embeddable
//data class Email(
//    @field:EmailValidation
//    @field:NotEmpty
//    @field:Column(name = "email", length = 50)
//    private val value: String
//) {
//
//a
//    fun getHost(): String? {
//        val index =value.indexOf("@");
//        return if(index != -1) value.substring(index + 1) else null
//    }
//
//    fun getId(): String? {
//        val index = value.indexOf("@")
//        return if (index != -1) value.substring(0, index) else null
//    }
//
//    companion object {
//        fun of(value: String) = Email(value);
//    }
//
//}