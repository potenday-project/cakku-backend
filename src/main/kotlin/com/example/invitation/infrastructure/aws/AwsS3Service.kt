package com.example.invitation.infrastructure.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.invitation.domain.file.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.OutputStream
import java.util.*

@Component
class AwsS3Service(
    private val amazonS3: AmazonS3,
    @Value("\${invitation.aws.s3.bucket-name}") private val bucketName: String,
) : StorageService {
    override fun upload(
        inputStream: InputStream,
        storageUploadRequestVo: StorageUploadRequestVo,
    ): StorageUploadResponseVo {
        val filename = UUID.randomUUID().toString()
        runCatching {
            amazonS3.putObject(PutObjectRequest(bucketName, filename, inputStream, ObjectMetadata().apply {
                contentType = storageUploadRequestVo.contentType
                contentLength = inputStream.available().toLong()
            }))
        }.onFailure {
            throw StorageUploadFailedException("Failed to upload file to S3", it)
        }
        return StorageUploadResponseVo(
            url = "https://$bucketName.s3.ap-northeast-2.amazonaws.com/$filename",
            name = filename,
            contentType = storageUploadRequestVo.contentType,
            size = storageUploadRequestVo.size,
        )
    }

    override fun download(outputStream: OutputStream, storageDownloadRequestVo: StorageDownloadRequestVo) {
        runCatching {
            amazonS3.getObject(
                GetObjectRequest(
                    bucketName,
                    storageDownloadRequestVo.filename,
                ),
            ).objectContent.copyTo(outputStream)
        }.onFailure {
            throw StorageDownloadFailedException("Failed to download file from S3", it)
        }
    }
}
