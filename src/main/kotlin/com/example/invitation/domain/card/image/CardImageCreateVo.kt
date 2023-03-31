package com.example.invitation.domain.card.image

import com.example.invitation.domain.card.template.CardTemplate
import com.example.invitation.domain.card.template.item.CardTemplateItem

data class CardImageCreateVo(
    val cardTemplate: CardTemplate,
    val cardTemplateItems: List<CardTemplateItem>,
)
