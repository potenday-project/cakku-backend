package com.example.invitation.domain.file

class StorageDownloadFailedException(
    override val message: String?,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause) {
}
