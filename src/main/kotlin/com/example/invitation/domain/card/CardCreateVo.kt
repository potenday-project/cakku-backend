package com.example.invitation.domain.card

data class CardCreateVo(
    val cardTemplateId: Long,
    val cardTemplateItemIds: List<Long>,
)
