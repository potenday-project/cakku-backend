package com.example.invitation.application.card

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.ui.card.CardTemplateResponse

fun CardTemplate.toDto(): CardTemplateResponse {
    return CardTemplateResponse(
        cardTemplateId = this.cardTemplateId,
        backgroundImageUrl = this.backgroundImageUrl,
        cardTemplateItems = this.cardTemplateItems.map { it.toDto() }
    )
}
