package com.example.invitation.ui.auth

import com.example.invitation.ui.member.MemberResponse

data class LoginResponse(
    val member: MemberResponse,
    val accessToken: String,
)