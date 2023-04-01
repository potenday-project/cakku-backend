package com.example.invitation.ui.invitation

import java.time.LocalDate
import java.time.LocalTime

data class InvitationRequest(
    val userName: String,
    val invitationTypeIndex: Int,
    val invitationDetailTypeId: Long,
    val summary: String,
    val description: String,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val place: String? = null,
    val cardTemplateItemIds: List<Long>,
)
