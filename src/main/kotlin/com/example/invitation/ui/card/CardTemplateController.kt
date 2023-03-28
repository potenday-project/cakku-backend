package com.example.invitation.ui.card

import com.example.invitation.application.card.CardApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/card-templates")
@RestController
class CardTemplateController(
    private val cardApplicationService: CardApplicationService,
) {
    @GetMapping
    fun getCardTemplates(): ApiResponse<List<CardTemplateResponse>> {
        return ApiResponse.success(
            data = cardApplicationService.getCardTemplates()
        )
    }
}
