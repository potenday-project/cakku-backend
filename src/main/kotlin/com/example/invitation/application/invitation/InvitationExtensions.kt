package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.Invitation
import com.example.invitation.domain.invitation.InvitationRequestVo
import com.example.invitation.domain.invitation.InvitationType
import com.example.invitation.domain.invitation.detail.InvitationDetailType
import com.example.invitation.ui.invitation.InvitationRequest
import com.example.invitation.ui.invitation.InvitationResponse

fun Invitation.toDto(): InvitationResponse {
    return InvitationResponse(
        invitationId = this.invitationId.toString(),
        cardId = this.card!!.cardId.toString(),
        userName = this.userName,
        invitationTypeIndex = this.invitationType.displayIndex,
        invitationDetailType = this.invitationDetailType.toDto(),
        summary = this.summary,
        description = this.description,
        date = this.date,
        time = this.time,
        place = this.place,
    )
}

fun InvitationRequest.toVo(invitationDetailType: InvitationDetailType): InvitationRequestVo {
    return InvitationRequestVo(
        userName = this.userName,
        invitationType = InvitationType.from(this.invitationTypeIndex),
        invitationDetailType = invitationDetailType,
        summary = this.summary,
        description = this.description,
        date = this.date,
        time = this.time,
        place = this.place,
    )
}
