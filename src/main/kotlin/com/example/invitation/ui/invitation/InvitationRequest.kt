package com.example.invitation.ui.invitation

import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.domain.invitation.InvitationType
import java.time.LocalDate
import java.time.LocalTime

data class InvitationRequest(
    val userName: String,
    val invitationType: InvitationType,
    val invitationDetailType: InvitationDetailType,
    val summary: String,
    val description: String,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val place: String? = null,
    val cardTemplateId: Long,
    val cardTemplateItemIds: List<Long>,
)
