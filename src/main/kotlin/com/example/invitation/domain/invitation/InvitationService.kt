package com.example.invitation.domain.invitation

import com.example.invitation.domain.invitation.draft.InvitationDraftService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface InvitationService {
    fun findById(invitationId: Long): Invitation?
    fun create(invitationDraftId: Long): Invitation
}

@Service
@Transactional(readOnly = true)
class InvitationServiceImpl(
    private val invitationRepository: InvitationRepository,
    private val invitationDraftService: InvitationDraftService,
) : InvitationService {
    override fun findById(invitationId: Long): Invitation? {
        return invitationRepository.findByIdOrNull(invitationId)
    }

    @Transactional
    override fun create(invitationDraftId: Long): Invitation {
        val invitationDraft = (invitationDraftService.findById(invitationDraftId)
            ?: throw IllegalArgumentException("InvitationDraft not found. invitationDraftId=$invitationDraftId"))
        val invitation = Invitation.from(invitationDraft)
        return invitationRepository.save(invitation)
    }

}

