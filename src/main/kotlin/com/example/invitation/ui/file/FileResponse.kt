package com.example.invitation.ui.file

data class FileResponse(
    val fileId: Long,
    val name: String,
    val contentType: String,
    val size: Long,
)
