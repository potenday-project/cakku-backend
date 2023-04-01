package com.example.invitation.ui.card

data class CardTemplateResponse(
    val cardTemplateId: String,
    val backgroundImageUrl: String,
    val cardTemplateItems: List<CardTemplateItemResponse>
)
