package com.example.invitation.domain.card

class CardNotFoundException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
