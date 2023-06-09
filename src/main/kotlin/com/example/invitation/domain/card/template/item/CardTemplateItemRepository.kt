package com.example.invitation.domain.card.template.item

import org.springframework.data.jpa.repository.JpaRepository

interface CardTemplateItemRepository : JpaRepository<CardTemplateItem, Long> {
    fun findByCardTemplateItemIdInAndDeleted(
        cardTemplateItemIds: Collection<Long>,
        deleted: Boolean
    ): List<CardTemplateItem>

    fun findByDeleted(deleted: Boolean): List<CardTemplateItem>
}
