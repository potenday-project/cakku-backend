package com.example.invitation.domain.invitation

import com.example.invitation.domain.invitation.detail.InvitationDetailType
import java.time.LocalDate
import java.time.LocalTime

data class InvitationRequestVo(
    val userName: String,
    val invitationType: InvitationType,
    val invitationDetailType: InvitationDetailType,
    val summary: String,
    val description: String,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val place: String? = null,
)
