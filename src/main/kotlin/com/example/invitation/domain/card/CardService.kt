package com.example.invitation.domain.card

import com.example.invitation.domain.card.template.CardTemplateNotFoundException
import com.example.invitation.domain.card.template.CardTemplateRepository
import com.example.invitation.domain.card.template.item.CardTemplateItemRepository
import com.example.invitation.domain.file.File
import com.example.invitation.domain.invitation.InvitationNotFoundException
import com.example.invitation.domain.invitation.InvitationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardService {
    fun getCards(): List<Card>

    fun createCard(
        cardCreateVo: CardCreateVo,
        invitationId: Long,
    ): Card

    fun updateCard(cardId: Long, file: File): Card
}

@Service
@Transactional(readOnly = true)
class CardServiceImpl(
    private val cardRepository: CardRepository,
    private val cardTemplateRepository: CardTemplateRepository,
    private val cardTemplateItemRepository: CardTemplateItemRepository,
    private val invitationRepository: InvitationRepository,
) : CardService {
    override fun getCards(): List<Card> {
        return cardRepository.findAll()
    }

    @Transactional
    override fun createCard(
        cardCreateVo: CardCreateVo,
        invitationId: Long,
    ): Card {
        val cardTemplate = cardTemplateRepository.findByIdOrNull(cardCreateVo.cardTemplateId)
            ?: throw CardTemplateNotFoundException("카드 템플릿이 존재하지 않습니다. cardTemplateId: ${cardCreateVo.cardTemplateId}")
        val cardTemplateItems = cardTemplateItemRepository.findByCardTemplateItemIdIn(cardCreateVo.cardTemplateItemIds)
        val invitation = invitationRepository.findByIdOrNull(invitationId)
            ?: throw InvitationNotFoundException("초대장이 존재하지 않습니다. invitationId: $invitationId")
        val card = Card(
            cardTemplate = cardTemplate,
            cardTemplateItems = cardTemplateItems,
            invitation = invitation,
        )
        invitation.card = card
        return cardRepository.save(card)
    }

    @Transactional
    override fun updateCard(cardId: Long, file: File): Card {
        return cardRepository.findByIdOrNull(cardId)
            ?.apply { update(file) }
            ?: throw CardNotFoundException("존재하지 않는 카드입니다. cardId: $cardId")
    }
}
