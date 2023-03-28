package com.example.invitation.domain.invitation.draft

import org.springframework.data.jpa.repository.JpaRepository

interface InvitationDraftRepository : JpaRepository<InvitationDraft, Long> {
}
