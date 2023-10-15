package com.miniproject.finkollector.member.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.miniproject.finkollector.member.dto.CompleteJoinRequest
import com.miniproject.finkollector.member.dto.JoinRequest
import com.miniproject.finkollector.member.service.MemberService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(MemberController::class)
class MemberControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var memberService: MemberService

    @Test
    fun `initiateJoin should return OK status`() {
        val joinRequest = JoinRequest("test@example.com", "password123")

        mockMvc.perform(
            post("/member/join/initiate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(joinRequest))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `completeJoin should return CREATED status`() {
        val completeJoinRequest = CompleteJoinRequest("test@example.com", "password123", "code123")

        mockMvc.perform(
            post("/member/join/complete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(completeJoinRequest))
        )
            .andExpect(status().isCreated)
    }
}
