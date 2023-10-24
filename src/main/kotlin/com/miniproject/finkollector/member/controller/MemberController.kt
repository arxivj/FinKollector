package com.miniproject.finkollector.member.controller

import com.miniproject.finkollector.member.dto.CompleteJoinRequest
import com.miniproject.finkollector.member.dto.JoinRequest
import com.miniproject.finkollector.member.dto.JoinResponse
import com.miniproject.finkollector.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/join/initiate")
    @Operation(
        summary = "[회원 가입 초기화]",
        description = "[사용ㄴㄴ]: 회원 가입시 인증 코드를 이메일로 전송하는 메서드로, 존재하지 않는 이메일로 발송시 오류 메일이 계속옴. 사용ㄴㄴ",
        responses = [
            ApiResponse(responseCode = "200", description = "이메일 전송 성공",
                content = [Content(schema = Schema(implementation = JoinResponse::class))]),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun initiateJoin(@RequestBody request: JoinRequest): ResponseEntity<JoinResponse> {
        memberService.initiateJoin(request)
        return ResponseEntity.ok(JoinResponse("이메일 인증 코드가 전송되었습니다. 메일함을 확인해주세요."))
    }

    @PostMapping("/join/complete")
    @Operation(
        summary = "[회원 가입 완료]",
        description = "이메일로 받은 인증 코드를 사용하여 회원 가입",
        responses = [
            ApiResponse(responseCode = "201", description = "회원 가입 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "500", description = "서버 오류")
        ]
    )
    fun completeJoin(@RequestBody request: CompleteJoinRequest): ResponseEntity<Void> {
        request.completeRegistrationWith(memberService)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}

// CompleteJoinRequest 확장 함수
private fun CompleteJoinRequest.completeRegistrationWith(service: MemberService) {
    service.completeRegistration(this.email, this.code, this.password)
}
