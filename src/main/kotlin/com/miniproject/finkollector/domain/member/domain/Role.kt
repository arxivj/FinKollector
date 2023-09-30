package com.miniproject.finkollector.domain.member.domain

enum class Role{
    ROLE_USER,
    ROLE_ADMIN;

    override fun toString(): String { // 사실 굳이 override 하지 않아도 kotlin enum class에서 자동으로 지원해준다
        return name; // name property를 확인하기 위해 override한 코드
    }
}