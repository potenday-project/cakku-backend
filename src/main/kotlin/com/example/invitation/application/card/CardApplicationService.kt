package com.example.invitation.application.card

import com.example.invitation.domain.card.template.CardTemplateService
import com.example.invitation.ui.card.CardTemplateResponse
import org.springframework.stereotype.Component

@Component
class CardApplicationService(
    private val cardTemplateService: CardTemplateService,
) {
    fun getCardTemplates(): List<CardTemplateResponse> {
        return cardTemplateService.getCardTemplates()
            .map { it.toDto() }
    }
}
