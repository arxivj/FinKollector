package com.miniproject.finkollector.member.controller

import com.miniproject.finkollector.member.dto.JoinRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConcurrentMemberControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test concurrent user registration`() = runBlocking(Dispatchers.Default) {
        val concurrentUsers = 1000
        val jobs = List(concurrentUsers) {
            async {
                val joinRequest = JoinRequest("testuser$it@example.com", "password123")
                mockMvc.perform(
                    post("/member/join/initiate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(joinRequest.toJson())
                )
                    .andExpect(status().isOk)
            }
        }

        jobs.forEach { it.await() }
    }
}

fun JoinRequest.toJson(): String {
    return """{"email": "${this.email}", "password": "${this.password}"}"""
}
