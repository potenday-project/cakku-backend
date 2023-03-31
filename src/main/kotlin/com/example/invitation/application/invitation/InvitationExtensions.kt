package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.Invitation
import com.example.invitation.domain.invitation.InvitationRequestVo
import com.example.invitation.ui.invitation.InvitationRequest
import com.example.invitation.ui.invitation.InvitationResponse

fun Invitation.toDto(): InvitationResponse {
    return InvitationResponse(
        invitationId = this.invitationId,
        cardId = this.card!!.cardId,
        userName = this.userName,
        invitationType = this.invitationType,
        invitationDetailType = this.invitationDetailType,
        summary = this.summary,
        description = this.description,
        date = this.date,
        time = this.time,
        place = this.place,
    )
}

fun InvitationRequest.toVo(): InvitationRequestVo {
    return InvitationRequestVo(
        userName = this.userName,
        invitationType = this.invitationType,
        invitationDetailType = this.invitationDetailType,
        summary = this.summary,
        description = this.description,
        date = this.date,
        time = this.time,
        place = this.place,
    )
}
