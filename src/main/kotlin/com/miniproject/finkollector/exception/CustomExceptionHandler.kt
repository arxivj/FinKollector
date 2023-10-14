package com.miniproject.finkollector.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(InvalidEmailException::class)
    fun handleInvalidEmail(exception: InvalidEmailException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }

    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmail(exception: DuplicateEmailException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(exception.message)
    }

}