package com.example.invitation.ui.invitation

data class InvitationDetailTypeResponse(
    val invitationDetailTypeId: String,
    val invitationType: String,
    val invitationTypeIndex: Int,
    val name: String,
    val emoji: String,
    val description: String,
)
