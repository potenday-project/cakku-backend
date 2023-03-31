package com.example.invitation.ui.invitation

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import java.time.LocalDateTime

data class InvitationRequest(
    val hostName: String,
    val invitationType: InvitationType,
    val invitationDetailType: InvitationDetailType,
    val summary: String,
    val description: String,
    val startedAt: LocalDateTime? = null,
    val location: String? = null,
    val cardTemplateId: Long,
    val cardTemplateItemIds: List<Long>,
)
