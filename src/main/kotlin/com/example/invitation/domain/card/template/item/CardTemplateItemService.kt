package com.example.invitation.domain.card.template.item

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardTemplateItemService {
    fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem>
    fun findAll(): List<CardTemplateItem>
}

@Service
@Transactional(readOnly = true)
class CardTemplateItemServiceImpl(
    private val cardTemplateItemRepository: CardTemplateItemRepository,
) : CardTemplateItemService {
    override fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem> {
        return cardTemplateItemRepository.findByCardTemplateItemIdInAndDeleted(cardTemplateItemIds, false)
    }

    override fun findAll(): List<CardTemplateItem> {
        return cardTemplateItemRepository.findByDeleted(false)
    }
}

