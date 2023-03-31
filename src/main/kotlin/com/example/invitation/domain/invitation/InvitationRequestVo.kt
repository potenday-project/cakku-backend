package com.example.invitation.domain.invitation

import java.time.LocalDateTime

data class InvitationRequestVo(
    val hostName: String,
    val invitationType: InvitationType,
    val invitationDetailType: InvitationDetailType,
    val summary: String,
    val description: String,
    val startedAt: LocalDateTime? = null,
    val location: String? = null,
)
