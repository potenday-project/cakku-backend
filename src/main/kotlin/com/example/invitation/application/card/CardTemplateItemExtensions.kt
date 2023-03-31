package com.example.invitation.application.card

import com.example.invitation.domain.card.template.item.CardTemplateItem
import com.example.invitation.ui.card.CardTemplateItemResponse

fun CardTemplateItem.toDto(): CardTemplateItemResponse {
    return CardTemplateItemResponse(
        cardTemplateItemId = this.cardTemplateItemId,
        name = this.name,
        emoji = this.emoji,
    )
}
