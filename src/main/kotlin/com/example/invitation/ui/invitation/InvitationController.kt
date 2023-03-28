package com.example.invitation.ui.invitation

import com.example.invitation.application.invitation.InvitationApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}
