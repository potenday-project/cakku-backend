package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.detail.InvitationDetailType
import com.example.invitation.ui.invitation.InvitationDetailTypeResponse

fun InvitationDetailType.toDto(): InvitationDetailTypeResponse {
    return InvitationDetailTypeResponse(
        invitationDetailTypeId = invitationDetailTypeId.toString(),
        invitationType = invitationType.name,
        invitationTypeIndex = invitationType.displayIndex,
        name = name,
        emoji = emoji,
        description = description,
    )
}
