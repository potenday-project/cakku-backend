package com.example.invitation.domain.card.template

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardTemplateService {
    fun getCardTemplates(): List<CardTemplate>
    fun getCardTemplate(cardTemplateId: Long): CardTemplate
}

@Service
@Transactional(readOnly = true)
class CardTemplateServiceImpl(
    private val cardTemplateRepository: CardTemplateRepository,
) : CardTemplateService {
    override fun getCardTemplates(): List<CardTemplate> {
        return cardTemplateRepository.findAll()
    }

    override fun getCardTemplate(cardTemplateId: Long): CardTemplate {
        return cardTemplateRepository.findByIdOrNull(cardTemplateId)
            ?: throw CardTemplateNotFoundException("존재하지 않는 카드 템플릿입니다. cardTemplateId: $cardTemplateId")
    }
}
