package com.example.invitation.domain.invitation.detail

import com.example.invitation.domain.invitation.InvitationType
import org.springframework.data.jpa.repository.JpaRepository

interface InvitationDetailTypeRepository : JpaRepository<InvitationDetailType, Long> {
    fun findByInvitationType(invitationType: InvitationType?): List<InvitationDetailType>
}
