package com.example.invitation.application.card

import com.example.invitation.domain.card.Card
import com.example.invitation.ui.card.CardResponse

fun Card.toDto(): CardResponse {
    @Suppress("SimpleRedundantLet")
    return CardResponse(
        cardId = this.cardId.toString(),
        fileId = this.file?.let { it.fileId.toString() }
    )
}
