package com.example.invitation.domain.file

data class StorageUploadResponseVo(
    val url: String,
    val name: String,
    val contentType: String,
    val size: Long,
)
