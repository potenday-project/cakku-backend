package com.example.invitation.domain.invitation.detail

import com.example.invitation.domain.invitation.InvitationType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface InvitationDetailTypeService {
    fun getOne(invitationDetailTypeId: Long): InvitationDetailType
    fun getInvitationDetailTypes(invitationType: InvitationType?): List<InvitationDetailType>
}

@Service
@Transactional(readOnly = true)
class InvitationDetailTypeServiceImpl(
    private val invitationDetailTypeRepository: InvitationDetailTypeRepository,
) : InvitationDetailTypeService {
    override fun getOne(invitationDetailTypeId: Long): InvitationDetailType {
        return invitationDetailTypeRepository.findByIdOrNull(invitationDetailTypeId)
            ?: throw InvitationDetailTypeNotFoundException("InvitationDetailType 이 존재하지 않습니다. $invitationDetailTypeId")
    }

    override fun getInvitationDetailTypes(invitationType: InvitationType?): List<InvitationDetailType> {
        return invitationDetailTypeRepository.findByInvitationType(invitationType)
    }
}
