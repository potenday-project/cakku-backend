package com.example.invitation.domain.invitation

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface InvitationService {
    fun findById(invitationId: Long): Invitation?
    fun create(invitationRequestVo: InvitationRequestVo): Invitation
}

@Service
@Transactional(readOnly = true)
class InvitationServiceImpl(
    private val invitationRepository: InvitationRepository,
) : InvitationService {
    override fun findById(invitationId: Long): Invitation? {
        return invitationRepository.findByIdOrNull(invitationId)
    }

    @Transactional
    override fun create(invitationRequestVo: InvitationRequestVo): Invitation {
        val invitation = Invitation.from(invitationRequestVo)
        return invitationRepository.save(invitation)
    }

}

