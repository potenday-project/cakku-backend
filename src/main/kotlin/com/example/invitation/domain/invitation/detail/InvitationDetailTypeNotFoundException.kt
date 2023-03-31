package com.example.invitation.domain.invitation.detail

class InvitationDetailTypeNotFoundException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
