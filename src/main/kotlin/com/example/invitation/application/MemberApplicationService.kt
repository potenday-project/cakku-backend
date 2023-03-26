package com.example.invitation.application

import com.example.invitation.domain.member.MemberService
import com.example.invitation.domain.member.ProviderType
import com.example.invitation.ui.member.MemberResponse
import org.springframework.stereotype.Component

@Component
class MemberApplicationService(
    private val memberService: MemberService,
) {
    fun getMembers(): List<MemberResponse> {
        return memberService.getMembers().map { it.toDto() }
    }

    fun login(
        providerType: ProviderType,
        providerUserId: String,
    ): MemberResponse {
        val member = memberService.findMember(
            providerType = providerType,
            providerUserId = providerUserId,
        ) ?: memberService.createMember(
            providerType = providerType,
            providerUserId = providerUserId,
        )
        return member.toDto()
    }
}