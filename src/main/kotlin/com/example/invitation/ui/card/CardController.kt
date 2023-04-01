package com.example.invitation.ui.card

import com.example.invitation.application.card.CardApplicationService
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/cards")
@RestController
class CardController(
    private val cardApplicationService: CardApplicationService,
) {
    @GetMapping("/{cardId}")
    fun getCard(
        @PathVariable cardId: Long,
    ): ApiResponse<CardResponse> {
        return ApiResponse.success(
            data = cardApplicationService.getCard(cardId)
        )
    }
}
