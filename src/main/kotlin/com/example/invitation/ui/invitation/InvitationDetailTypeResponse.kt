package com.example.invitation.ui.invitation

data class InvitationDetailTypeResponse(
    val invitationDetailTypeId: Long,
    val invitationType: String,
    val name: String,
    val emoji: String,
    val description: String,
)
