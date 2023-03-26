package com.example.invitation.domain.file

data class FileCreateVo(
    val name: String,
    val url: String,
    val contentType: String,
    val size: Long,
)
