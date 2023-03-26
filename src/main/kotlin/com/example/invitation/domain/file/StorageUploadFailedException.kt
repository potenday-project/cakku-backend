package com.example.invitation.domain.file

class StorageUploadFailedException(
    override val message: String?,
    override val cause: Throwable?,
) : RuntimeException(message, cause) {
}
