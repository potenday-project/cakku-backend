package com.example.invitation.application.file

import com.example.invitation.domain.file.File
import com.example.invitation.ui.file.FileResponse

fun File.toFileResponse(): FileResponse {
    return FileResponse(
        fileId = this.fileId.toString(),
        name = this.name,
        contentType = this.contentType,
        size = this.size,
    )
}
