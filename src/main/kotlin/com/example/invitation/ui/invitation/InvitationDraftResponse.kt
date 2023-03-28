package com.example.invitation.ui.invitation

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import java.time.LocalDateTime

data class InvitationDraftResponse(
    val invitationDraftId: Long,
    val hostName: String? = null,
    val invitationType: InvitationType? = null,
    val invitationDetailType: InvitationDetailType? = null,
    val summary: String? = null,
    val description: String? = null,
    val startedAt: LocalDateTime? = null,
    val location: String? = null,
)
