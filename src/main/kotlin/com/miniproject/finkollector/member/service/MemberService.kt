package com.miniproject.finkollector.member.service

import com.miniproject.finkollector.exception.DuplicateEmailException
import com.miniproject.finkollector.exception.InvalidEmailException
import com.miniproject.finkollector.member.domain.Member
import com.miniproject.finkollector.member.domain.OAuthProvider
import com.miniproject.finkollector.member.domain.Role
import com.miniproject.finkollector.member.dto.JoinRequest
import com.miniproject.finkollector.member.repository.MemberRepository
import com.miniproject.finkollector.model.Email
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
     * 회원 가입 프로세스 시작: 이메일 검증, 중복여부를 확인 후 인증코드를 포함한 이메일 전송
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

    private fun String.validateEmailFormat() {
        if (this.isBlank() || !this.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))) {
            throw InvalidEmailException("잘못된 이메일 형식입니다.")
        }
    }

    private fun ensureEmailIsNotDuplicate(email: String) {
        if (memberRepository.existsByEmail(email)) {
            throw DuplicateEmailException("이미 사용 중인 이메일입니다.")
        }
    }

    fun verifyEmail(email: String, providedCode: String): Boolean =
        emailCodeStore.isValidEmailCode(email, providedCode)

    /**
     * 이메일을 검증하고, 패스워드를 해싱한 후, 회원을 저장하여 가입을 완료
     */
    @Transactional
    fun completeRegistration(email: String, providedCode: String, password: String) {
        if (!verifyEmail(email, providedCode)) {
            throw InvalidEmailException("잘못된 이메일 인증 코드입니다.")
        }

        val member = createMember(email, password)
        memberRepository.save(member)
    }

    private fun createMember(email: String, password: String): Member {
        val salt = generateSalt()
        val hashedPassword = hashPassword(password, salt)

        return Member(
            email = Email(email),
            password = hashedPassword,
            salt = salt,
            roles = hashSetOf(Role.ROLE_USER),
            oauthClient = OAuthProvider.NONE
        )
    }
}
