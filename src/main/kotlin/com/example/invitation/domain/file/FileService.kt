package com.example.invitation.domain.file

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface FileService {
    fun create(fileCreateVo: FileCreateVo): File
}

@Service
@Transactional(readOnly = true)
class FileServiceImpl(
    private val fileRepository: FileRepository,
) : FileService {
    @Transactional
    override fun create(fileCreateVo: FileCreateVo): File {
        File(
            url = fileCreateVo.url,
            name = fileCreateVo.name,
            contentType = fileCreateVo.contentType,
            size = fileCreateVo.size,
            deleted = false,
        ).let {
            return fileRepository.save(it)
        }
    }
}

