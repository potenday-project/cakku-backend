package com.example.invitation.domain.invitation.draft

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import java.time.LocalDateTime

data class InvitationDraftUpdateVo(
    val hostName: String?,
    val invitationType: InvitationType?,
    val invitationDetailType: InvitationDetailType?,
    val summary: String?,
    val description: String?,
    val startedAt: LocalDateTime?,
    val location: String?,
)
