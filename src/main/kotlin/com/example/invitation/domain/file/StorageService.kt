package com.example.invitation.domain.file

import java.io.InputStream
import java.io.OutputStream

interface StorageService {
    fun upload(inputStream: InputStream, storageUploadRequestVo: StorageUploadRequestVo): StorageUploadResponseVo
    fun download(outputStream: OutputStream, storageDownloadRequestVo: StorageDownloadRequestVo)
}
