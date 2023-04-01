package com.example.invitation.ui.file

data class FileResponse(
    val fileId: String,
    val name: String,
    val contentType: String,
    val size: Long,
)
