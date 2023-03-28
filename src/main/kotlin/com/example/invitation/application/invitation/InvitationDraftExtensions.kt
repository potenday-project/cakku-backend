package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.draft.InvitationDraft
import com.example.invitation.domain.invitation.draft.InvitationDraftUpdateVo
import com.example.invitation.ui.invitation.InvitationDraftResponse
import com.example.invitation.ui.invitation.InvitationDraftUpdateRequest

fun InvitationDraft.toDto(): InvitationDraftResponse {
    return InvitationDraftResponse(
        invitationDraftId = this.invitationDraftId,
        hostName = this.hostName,
        invitationType = this.invitationType,
        invitationDetailType = this.invitationDetailType,
        summary = this.summary,
        description = this.description,
        startedAt = this.startedAt,
        location = this.location,
    )
}

fun InvitationDraftUpdateRequest.toVo(): InvitationDraftUpdateVo {
    return InvitationDraftUpdateVo(
        hostName = this.hostName,
        invitationType = this.invitationType,
        invitationDetailType = this.invitationDetailType,
        summary = this.summary,
        description = this.description,
        startedAt = this.startedAt,
        location = this.location,
    )
}
