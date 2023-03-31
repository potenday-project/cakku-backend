package com.example.invitation.domain.invitation

class InvitationNotFoundException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
