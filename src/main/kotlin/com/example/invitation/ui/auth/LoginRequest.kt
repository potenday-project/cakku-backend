package com.example.invitation.ui.auth

import com.example.invitation.domain.member.ProviderType

data class LoginRequest(
    val providerType: ProviderType,
    val providerUserId: String,
)
