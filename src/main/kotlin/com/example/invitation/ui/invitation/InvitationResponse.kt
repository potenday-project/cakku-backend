package com.example.invitation.ui.invitation

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import java.time.LocalDate
import java.time.LocalTime

data class InvitationResponse(
    val invitationId: Long,
    val cardId: Long,
    val hostName: String,
    val invitationType: InvitationType,
    val invitationDetailType: InvitationDetailType,
    val summary: String,
    val description: String,
    val date: LocalDate?,
    val time: LocalTime?,
    val location: String?,
)
