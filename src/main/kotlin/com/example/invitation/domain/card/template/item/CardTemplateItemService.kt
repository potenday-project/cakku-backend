package com.example.invitation.domain.card.template.item

import com.example.invitation.domain.card.template.CardTemplateRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardTemplateItemService {
    fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem>
    fun getCardTemplateItems(invitationDetailTypeId: Long?): List<CardTemplateItem>
    fun findAll(): List<CardTemplateItem>
}

@Service
@Transactional(readOnly = true)
class CardTemplateItemServiceImpl(
    private val cardTemplateItemRepository: CardTemplateItemRepository,
    private val cardTemplateRepository: CardTemplateRepository,
) : CardTemplateItemService {
    override fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem> {
        return cardTemplateItemRepository.findByCardTemplateItemIdInAndDeleted(cardTemplateItemIds, false)
    }

    override fun getCardTemplateItems(invitationDetailTypeId: Long?): List<CardTemplateItem> {
        return invitationDetailTypeId?.let {
            cardTemplateItemRepository.findByInvitationDetailType_invitationDetailTypeIdAndDeleted(it, false)
        } ?: cardTemplateItemRepository.findAll()
    }

    override fun findAll(): List<CardTemplateItem> {
        return cardTemplateItemRepository.findByDeleted(false)
    }
}

