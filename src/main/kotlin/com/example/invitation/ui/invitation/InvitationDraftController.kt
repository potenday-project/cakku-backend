package com.example.invitation.ui.invitation

import com.example.invitation.application.invitation.InvitationApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/invitation-drafts")
@RestController
class InvitationDraftController(
    private val invitationApplicationService: InvitationApplicationService,
) {
    @PostMapping
    fun createInvitationDraft(): ApiResponse<InvitationDraftResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.createInvitationDraft()
        )
    }

    @GetMapping("/{invitationDraftId}")
    fun getInvitationDraft(
        @PathVariable invitationDraftId: Long,
    ): ApiResponse<InvitationDraftResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.getInvitationDraft(invitationDraftId),
        )
    }

    @PutMapping("/{invitationDraftId}")
    fun updateInvitationDraft(
        @PathVariable invitationDraftId: Long,
        @RequestBody invitationDraftUpdateRequest: InvitationDraftUpdateRequest,
    ): ApiResponse<InvitationDraftResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.updateInvitationDraft(
                invitationDraftId,
                invitationDraftUpdateRequest,
            ),
        )
    }

    @PostMapping("/{invitationDraftId}/publish")
    fun publishInvitationDraft(
        @PathVariable invitationDraftId: Long,
    ): ApiResponse<InvitationResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.publishInvitationDraft(invitationDraftId),
        )
    }
}
