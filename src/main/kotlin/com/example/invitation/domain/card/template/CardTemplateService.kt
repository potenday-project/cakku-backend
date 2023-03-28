package com.example.invitation.domain.card.template

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardTemplateService {
    fun getCardTemplates(): List<CardTemplate>
}

@Service
@Transactional(readOnly = true)
class CardTemplateServiceImpl(
    private val cardTemplateRepository: CardTemplateRepository,
) : CardTemplateService {
    override fun getCardTemplates(): List<CardTemplate> {
        return cardTemplateRepository.findAll()
    }
}
