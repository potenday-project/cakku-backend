package com.example.invitation.ui.invitation

import com.example.invitation.application.invitation.InvitationApplicationService
import com.example.invitation.domain.analytics.AnalyticsEvent
import com.example.invitation.domain.analytics.AnalyticsEventPublished
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/invitations")
@RestController
class InvitationController(
    private val invitationApplicationService: InvitationApplicationService,
) {
    @GetMapping("/{invitationId}")
    fun getInvitation(
        @PathVariable invitationId: Long,
    ): ApiResponse<InvitationResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.getInvitation(invitationId),
        )
    }

    @PostMapping
    @AnalyticsEventPublished(event = AnalyticsEvent.INVITATION_CREATED)
    fun createInvitation(
        @RequestBody invitationRequest: InvitationRequest,
    ): ApiResponse<InvitationResponse> {
        return ApiResponse.success(
            data = invitationApplicationService.createInvitation(invitationRequest),
        )
    }
}

