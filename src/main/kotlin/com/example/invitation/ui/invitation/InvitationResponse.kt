package com.example.invitation.ui.invitation

import java.time.LocalDate
import java.time.LocalTime

data class InvitationResponse(
    val invitationId: String,
    val cardId: String,
    val userName: String,
    val invitationTypeIndex: Int,
    val invitationDetailType: InvitationDetailTypeResponse,
    val summary: String,
    val description: String,
    val date: LocalDate?,
    val time: LocalTime?,
    val place: String?,
)
