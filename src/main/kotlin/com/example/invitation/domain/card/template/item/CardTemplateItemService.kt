package com.example.invitation.domain.card.template.item

import com.example.invitation.domain.invitation.InvitationDetailType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardTemplateItemService {
    fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem>
    fun getCardTemplateItems(invitationDetailType: InvitationDetailType?): List<CardTemplateItem>
}

@Service
@Transactional(readOnly = true)
class CardTemplateItemServiceImpl(
    private val cardTemplateItemRepository: CardTemplateItemRepository,
) : CardTemplateItemService {
    override fun findByIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem> {
        return cardTemplateItemRepository.findByCardTemplateItemIdIn(cardTemplateItemIds)
    }

    override fun getCardTemplateItems(invitationDetailType: InvitationDetailType?): List<CardTemplateItem> {
        invitationDetailType?.let {
            return cardTemplateItemRepository.findByInvitationDetailType(it)
        } ?: return cardTemplateItemRepository.findAll()
    }
}

