package com.example.invitation.application

import com.example.invitation.domain.member.MemberService
import com.example.invitation.ui.MemberResponse
import org.springframework.stereotype.Component

@Component
class MemberApplicationService(
    private val memberService: MemberService,
) {
    fun getMembers(): List<MemberResponse> {
        return memberService.getMembers().map { it.toDto() }
    }
}