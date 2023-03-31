package com.example.invitation.domain.card.template

class CardTemplateNotFoundException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)
