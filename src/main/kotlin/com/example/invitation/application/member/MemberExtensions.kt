package com.example.invitation.application.member

import com.example.invitation.domain.member.Member
import com.example.invitation.ui.member.MemberResponse

fun Member.toDto(): MemberResponse {
    return MemberResponse(
        memberId = this.memberId,
    )
}
