package com.example.invitation.ui.auth

import com.example.invitation.application.MemberApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val memberApplicationService: MemberApplicationService,
) {
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest,
    ): ApiResponse<LoginResponse> {
        val member = memberApplicationService.login(
            loginRequest.providerType,
            loginRequest.providerUserId,
        )
        return ApiResponse.success(
            data = LoginResponse(
                member = member,
                accessToken = "accessToken",
            )
        )
    }
}