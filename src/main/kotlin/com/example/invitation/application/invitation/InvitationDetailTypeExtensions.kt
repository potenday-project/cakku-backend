package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.detail.InvitationDetailType
import com.example.invitation.ui.invitation.InvitationDetailTypeResponse

fun InvitationDetailType.toDto(): InvitationDetailTypeResponse {
    return InvitationDetailTypeResponse(
        invitationDetailTypeId = invitationDetailTypeId,
        invitationType = invitationType.name,
        name = name,
        emoji = emoji,
        description = description,
    )
}
