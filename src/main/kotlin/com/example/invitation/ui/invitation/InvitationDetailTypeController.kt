package com.example.invitation.ui.invitation

import com.example.invitation.application.invitation.InvitationApplicationService
import com.example.invitation.domain.invitation.InvitationType
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/invitation-detail-types")
@RestController
class InvitationDetailTypeController(
    private val invitationApplicationService: InvitationApplicationService,
) {
    @GetMapping
    fun getInvitationDetailTypes(
        @RequestParam(required = false) invitationTypeIndex: Int?,
    ): ApiResponse<List<InvitationDetailTypeResponse>> {
        return ApiResponse.success(
            data = invitationApplicationService.getInvitationDetailTypes(
                invitationType = invitationTypeIndex?.let { InvitationType.from(it) }
            ),
        )
    }
}
