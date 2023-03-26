package com.example.invitation.domain.file

class FileNotFoundException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause) {
}
