package com.example.invitation.application.file

import com.example.invitation.domain.file.*
import com.example.invitation.ui.file.FileResponse
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.OutputStream

@Component
class FileApplicationService(
    private val fileService: FileService,
    private val storageService: StorageService,
) {
    fun upload(inputStream: InputStream, fileUploadVo: FileUploadVo): FileResponse {
        return storageService.upload(
            inputStream = inputStream,
            storageUploadRequestVo = StorageUploadRequestVo(
                contentType = fileUploadVo.contentType,
                size = fileUploadVo.size,
            ),
        ).let {
            fileService.create(
                fileCreateVo = FileCreateVo(
                    url = it.url,
                    name = it.name,
                    contentType = it.contentType,
                    size = it.size,
                )
            )
        }.toFileResponse()
    }

    fun download(fileId: Long, outputStream: OutputStream) {
        val file = fileService.findById(fileId) ?: throw FileNotFoundException("File not found. fileId: $fileId")
        storageService.download(
            outputStream,
            StorageDownloadRequestVo(
                fileId = file.fileId,
                filename = file.name,
            ),
        )
    }
}
