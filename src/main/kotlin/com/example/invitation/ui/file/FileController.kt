package com.example.invitation.ui.file

import com.example.invitation.application.file.FileApplicationService
import com.example.invitation.application.file.FileUploadVo
import com.example.invitation.ui.ApiResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1")
class FileController(
    private val fileApplicationService: FileApplicationService,
) {
    @PostMapping("/files", consumes = ["multipart/form-data"])
    fun upload(
        @RequestPart file: MultipartFile,
    ): ApiResponse<FileResponse> {
        return ApiResponse.success(
            data = fileApplicationService.upload(
                inputStream = file.inputStream,
                fileUploadVo = FileUploadVo(
                    name = file.originalFilename ?: "",
                    contentType = file.contentType ?: "",
                    size = file.size,
                )
            )
        )
    }

    @GetMapping("/files/{fileId}")
    fun download(
        @PathVariable fileId: Long,
        response: HttpServletResponse,
    ) {
        fileApplicationService.download(
            fileId = fileId,
            outputStream = response.outputStream,
            response = response,
        )
    }
}
