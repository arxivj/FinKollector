package com.miniproject.finkollector.member.controller

import com.miniproject.finkollector.member.dto.CompleteJoinRequest
import com.miniproject.finkollector.member.dto.JoinRequest
import com.miniproject.finkollector.member.dto.JoinResponse
import com.miniproject.finkollector.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/join/initiate")
    fun initiateJoin(@RequestBody request: JoinRequest): ResponseEntity<JoinResponse> {
        memberService.initiateJoin(request)
        return ResponseEntity.ok(JoinResponse("이메일 인증 코드가 전송되었습니다. 메일함을 확인해주세요."))
    }

    @PostMapping("/join/complete")
    fun completeJoin(@RequestBody request: CompleteJoinRequest): ResponseEntity<Void> {
        request.completeRegistrationWith(memberService)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}

// CompleteJoinRequest 확장 함수
private fun CompleteJoinRequest.completeRegistrationWith(service: MemberService) {
    service.completeRegistration(this.email, this.code, this.password)
}
