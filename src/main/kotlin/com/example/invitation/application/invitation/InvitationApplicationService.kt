package com.example.invitation.application.invitation

import com.example.invitation.domain.invitation.InvitationService
import com.example.invitation.domain.invitation.draft.InvitationDraftService
import com.example.invitation.ui.invitation.InvitationDraftResponse
import com.example.invitation.ui.invitation.InvitationDraftUpdateRequest
import com.example.invitation.ui.invitation.InvitationResponse
import org.springframework.stereotype.Component

@Component
class InvitationApplicationService(
    private val invitationDraftService: InvitationDraftService,
    private val invitationService: InvitationService,
) {
    fun createInvitationDraft(): InvitationDraftResponse {
        return invitationDraftService.create().toDto()
    }

    fun getInvitationDraft(invitationDraftId: Long): InvitationDraftResponse {
        val invitationDraft = invitationDraftService.findById(invitationDraftId)
        return invitationDraft?.toDto() ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")
    }

    fun updateInvitationDraft(
        invitationDraftId: Long,
        invitationDraftUpdateRequest: InvitationDraftUpdateRequest,
    ): InvitationDraftResponse {
        val invitationDraft =
            invitationDraftService.findById(invitationDraftId) ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")
        invitationDraft.update(invitationDraftUpdateRequest.toVo())
        return invitationDraft.toDto()
    }

    fun getInvitation(invitationId: Long): InvitationResponse {
        val invitation = invitationService.findById(invitationId)
        return invitation?.toDto() ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")
    }

    fun publishInvitationDraft(invitationDraftId: Long): InvitationResponse {
        invitationDraftService.publish(invitationDraftId)
        val invitation = invitationService.create(invitationDraftId)
        return invitation.toDto()
    }
}
