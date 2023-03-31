package com.example.invitation.domain.card.template.item

import org.springframework.data.jpa.repository.JpaRepository

interface CardTemplateItemRepository : JpaRepository<CardTemplateItem, Long> {
    fun findByCardTemplateItemIdIn(cardTemplateItemIds: List<Long>): List<CardTemplateItem>
}
