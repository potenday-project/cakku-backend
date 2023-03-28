package com.example.invitation.domain.invitation.draft

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface InvitationDraftService {
    fun create(): InvitationDraft
    fun update(invitationDraftId: Long, invitationDraftUpdateVo: InvitationDraftUpdateVo): InvitationDraft
    fun findById(invitationDraftId: Long): InvitationDraft?
    fun publish(invitationDraftId: Long)
}

@Service
@Transactional(readOnly = true)
class InvitationDraftServiceImpl(
    private val invitationDraftRepository: InvitationDraftRepository,
) : InvitationDraftService {

    @Transactional
    override fun create(): InvitationDraft {
        return invitationDraftRepository.save(InvitationDraft())
    }

    @Transactional
    override fun update(invitationDraftId: Long, invitationDraftUpdateVo: InvitationDraftUpdateVo): InvitationDraft {
        val invitationDraft = invitationDraftRepository.findByIdOrNull(invitationDraftId)
            ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")

        invitationDraft.update(invitationDraftUpdateVo)
        return invitationDraft
    }

    override fun findById(invitationDraftId: Long): InvitationDraft? {
        return invitationDraftRepository.findByIdOrNull(invitationDraftId)
    }

    override fun publish(invitationDraftId: Long) {
        val invitationDraft = invitationDraftRepository.findByIdOrNull(invitationDraftId)
            ?: throw IllegalArgumentException("초대장이 존재하지 않습니다.")
        invitationDraft.publish()
    }
}
