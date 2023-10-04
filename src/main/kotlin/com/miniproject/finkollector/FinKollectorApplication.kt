package com.miniproject.finkollector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class FinKollectorApplication

fun main(args: Array<String>) {
    runApplication<FinKollectorApplication>(*args)
}
