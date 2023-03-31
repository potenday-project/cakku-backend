package com.example.invitation.application.card

import com.example.invitation.domain.card.template.CardTemplateService
import com.example.invitation.domain.card.template.item.CardTemplateItemService
import com.example.invitation.domain.invitation.InvitationDetailType
import com.example.invitation.ui.card.CardTemplateItemResponse
import com.example.invitation.ui.card.CardTemplateResponse
import org.springframework.stereotype.Component

@Component
class CardApplicationService(
    private val cardTemplateService: CardTemplateService,
    private val cardTemplateItemService: CardTemplateItemService,
) {
    fun getCardTemplates(): List<CardTemplateResponse> {
        return cardTemplateService.getCardTemplates()
            .map { it.toDto() }
    }

    fun getCardTemplateItems(
        invitationDetailType: InvitationDetailType?,
    ): List<CardTemplateItemResponse> {
        return cardTemplateItemService.getCardTemplateItems(
            invitationDetailType = invitationDetailType
        ).map { it.toDto() }
    }
}
