package com.miniproject.finkollector.member.repository

import com.miniproject.finkollector.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

/**
 * 회원 정보를 관리하기 위한 Repository 인터페이스
 */
interface MemberRepository : JpaRepository<Member, Long> {

    /**
     * 주어진 이메일에 해당하는 회원의 존재 여부를 반환한다.
     *
     * @param email 확인하고자 하는 이메일 주소
     * @return 해당 이메일을 가진 회원이 존재하면 true, 그렇지 않으면 false
     */
    fun existsByEmail(email: String): Boolean
}
