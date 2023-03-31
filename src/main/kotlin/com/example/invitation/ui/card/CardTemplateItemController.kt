package com.example.invitation.ui.card

import com.example.invitation.application.card.CardApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/card-template-items")
@RestController
class CardTemplateItemController(
    private val cardApplicationService: CardApplicationService
) {
    @GetMapping
    fun getCardTemplateItems(
        @RequestParam(required = false) invitationDetailTypeId: Long?
    ): ApiResponse<List<CardTemplateItemResponse>> {
        return ApiResponse.success(
            data = cardApplicationService.getCardTemplateItems(invitationDetailTypeId)
        )
    }
}
