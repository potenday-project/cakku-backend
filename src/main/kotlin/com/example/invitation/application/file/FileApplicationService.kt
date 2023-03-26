package com.example.invitation.application.file

import com.example.invitation.domain.file.FileCreateVo
import com.example.invitation.domain.file.FileService
import com.example.invitation.domain.file.StorageService
import com.example.invitation.domain.file.StorageUploadRequestVo
import com.example.invitation.ui.file.FileResponse
import org.springframework.stereotype.Component
import java.io.InputStream

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
}
