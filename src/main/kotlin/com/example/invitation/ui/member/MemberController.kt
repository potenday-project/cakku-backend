package com.example.invitation.ui.member

import com.example.invitation.application.MemberApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class MemberController(
    private val memberApplicationService: MemberApplicationService,
) {
    @GetMapping("/members")
    fun getMembers(): ApiResponse<List<MemberResponse>> {
        return ApiResponse.success(
            contents = memberApplicationService.getMembers(),
        )
    }
}
