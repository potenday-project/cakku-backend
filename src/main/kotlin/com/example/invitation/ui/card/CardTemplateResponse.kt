package com.example.invitation.ui.card

data class CardTemplateResponse(
    val cardTemplateId: Long,
    val backgroundImageUrl: String,
    val cardTemplateItems: List<CardTemplateItemResponse>
)
