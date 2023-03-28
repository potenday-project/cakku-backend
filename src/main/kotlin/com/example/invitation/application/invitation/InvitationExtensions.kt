package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.Invitation
import com.example.invitation.ui.invitation.InvitationResponse

fun Invitation.toDto(): InvitationResponse {
    return InvitationResponse(
        invitationId = this.invitationId,
        hostName = this.hostName,
        invitationType = this.invitationType,
        invitationDetailType = this.invitationDetailType,
        summary = this.summary,
        description = this.description,
        startedAt = this.startedAt,
        location = this.location,
    )
}
