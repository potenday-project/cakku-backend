package com.example.invitation.domain.card.template.item

import com.example.invitation.domain.invitation.InvitationDetailType
import org.springframework.data.jpa.repository.JpaRepository

interface CardTemplateItemRepository : JpaRepository<CardTemplateItem, Long> {
    fun findByCardTemplateItemIdIn(cardTemplateItemIds: Collection<Long>): List<CardTemplateItem>
    fun findByInvitationDetailType(invitationDetailType: InvitationDetailType): List<CardTemplateItem>
}
