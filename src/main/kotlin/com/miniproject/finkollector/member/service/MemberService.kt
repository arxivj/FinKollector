package com.miniproject.finkollector.member.service

import com.miniproject.finkollector.exception.DuplicateEmailException
import com.miniproject.finkollector.exception.InvalidEmailException
import com.miniproject.finkollector.member.domain.Member
import com.miniproject.finkollector.member.domain.OAuthProvider
import com.miniproject.finkollector.member.domain.Role
import com.miniproject.finkollector.member.dto.JoinRequest
import com.miniproject.finkollector.member.repository.MemberRepository
import com.miniproject.finkollector.util.PasswordEncoder.generateSalt
import com.miniproject.finkollector.util.PasswordEncoder.hashPassword
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberService(
    private val emailCodeStore: EmailCodeStore,
    private val memberRepository: MemberRepository,
    private val emailSenderService: EmailSenderService
) {

    /**
     * 회원 가입 프로세스를 시작: 이메일 형식을 검증하고 이메일의 중복 여부를 확인한 후,
     * 인증 코드를 포함한 이메일을 전송.
     *
     * @param request 회원 가입 요청 정보를 담고 있는 [JoinRequest] 객체.
     * @return 생성된 인증 코드.
     * @throws InvalidEmailException 잘못된 이메일 형식일 때 발생.
     * @throws DuplicateEmailException 이메일이 이미 사용 중일 때 발생.
     */
    @Transactional(readOnly = true)
    fun initiateJoin(request: JoinRequest): String {
        request.email.validateEmailFormat()
        ensureEmailIsNotDuplicate(request.email)

        return UUID.randomUUID().toString().also { code ->
            emailCodeStore.saveEmailCode(request.email, code)
            emailSenderService.sendVerificationEmail(request.email, code)
        }
    }

    /**
     * 이메일 형식을 검증.
     *
     * @receiver 검증할 이메일 문자열.
     * @throws InvalidEmailException 이메일 형식이 유효하지 않을 때 발생.
     */
    private fun String.validateEmailFormat() {
        if (this.isBlank() || !this.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            throw InvalidEmailException("잘못된 이메일 형식입니다.")
        }
    }

    /**
     * 이메일의 중복 여부를 확인.
     *
     * @param email 검증할 이메일 주소.
     * @throws DuplicateEmailException 이메일이 이미 사용 중일 때 발생.
     */
    private fun ensureEmailIsNotDuplicate(email: String) {
        if (memberRepository.existsByEmail(email)) {
            throw DuplicateEmailException("이미 사용 중인 이메일입니다.")
        }
    }

    /**
     * 제공된 인증 코드가 유효한지 확인.
     *
     * @param email 인증을 요청하는 회원의 이메일 주소.
     * @param providedCode 사용자에게 제공된 인증 코드.
     * @return 인증 코드의 유효성 여부.
     */
    fun verifyEmail(email: String, providedCode: String): Boolean =
        emailCodeStore.isValidEmailCode(email, providedCode)

    /**
     * 이메일 인증을 완료하고 패스워드를 해싱한 후, 회원 정보를 저장.
     *
     * @param email 가입을 완료하려는 회원의 이메일 주소.
     * @param providedCode 이메일 인증 코드.
     * @param password 회원의 비밀번호.
     * @throws InvalidEmailException 이메일 인증 코드가 유효하지 않을 때 발생.
     */
    @Transactional
    fun completeRegistration(email: String, providedCode: String, password: String) {
        if (!verifyEmail(email, providedCode)) {
            throw InvalidEmailException("잘못된 이메일 인증 코드입니다.")
        }

        val member = createMember(email, password)
        memberRepository.save(member)
    }

    /**
     * 회원 객체 생성.
     *
     * @param email 회원의 이메일 주소.
     * @param password 회원의 비밀번호.
     * @return 생성된 [Member] 객체.
     */
    private fun createMember(email: String, password: String): Member {
        val salt = generateSalt()
        val hashedPassword = hashPassword(password, salt)

        return Member(
            email = email,
            password = hashedPassword,
            salt = salt,
            roles = hashSetOf(Role.ROLE_USER),
            oauthClient = OAuthProvider.NONE
        )
    }
}
