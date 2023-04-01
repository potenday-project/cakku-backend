package com.example.invitation.application.invitation

import com.example.invitation.domain.card.CardCreateVo
import com.example.invitation.domain.card.CardService
import com.example.invitation.domain.card.image.CardImageCreateVo
import com.example.invitation.domain.card.image.CardImageService
import com.example.invitation.domain.invitation.InvitationService
import com.example.invitation.domain.invitation.InvitationType
import com.example.invitation.domain.invitation.detail.InvitationDetailTypeService
import com.example.invitation.ui.invitation.InvitationDetailTypeResponse
import com.example.invitation.ui.invitation.InvitationRequest
import com.example.invitation.ui.invitation.InvitationResponse
import org.springframework.stereotype.Component

@Component
class InvitationApplicationService(
    private val invitationService: InvitationService,
    private val invitationDetailTypeService: InvitationDetailTypeService,
    private val cardService: CardService,
    private val cardImageService: CardImageService,
) {
    fun getInvitation(invitationId: Long): InvitationResponse {
        val invitation = invitationService.findById(invitationId)
        return invitation?.toDto() ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")
    }

    fun createInvitation(invitationRequest: InvitationRequest): InvitationResponse {
        // 초대장 생성
        val invitationDetailType = invitationDetailTypeService.getOne(invitationRequest.invitationDetailTypeId.toLong())
        val invitation = invitationService.create(
            invitationRequest.toVo(invitationDetailType),
        )
        // 카드 생성
        val card = cardService.createCard(
            cardCreateVo = CardCreateVo(
                cardTemplateId = invitationDetailType.cardTemplate.cardTemplateId,
                cardTemplateItemIds = invitationRequest.cardTemplateItemIds.map { it.toLong() },
            ),
            invitationId = invitation.invitationId,
        )
        // 카드 파일 생성
        val cardImageFile = cardImageService.createImage(
            cardImageCreateVo = CardImageCreateVo(
                cardTemplate = card.cardTemplate,
                cardTemplateItems = card.cardTemplateItems,
            ),
        )
        // 카드 주소 업데이트
        cardService.updateCard(card.cardId, cardImageFile)
        return invitation.toDto()
    }

    fun getInvitationDetailTypes(invitationType: InvitationType?): List<InvitationDetailTypeResponse> {
        return invitationDetailTypeService.getInvitationDetailTypes(invitationType)
            .map { it.toDto() }
    }
}
