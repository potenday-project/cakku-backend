package com.example.invitation.domain.card

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface CardService {
    fun getCards(): List<Card>
}

@Service
@Transactional(readOnly = true)
class CardServiceImpl(
    private val cardRepository: CardRepository,
) : CardService {
    override fun getCards(): List<Card> {
        return cardRepository.findAll()
    }
}
