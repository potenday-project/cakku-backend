package com.example.invitation.domain.card.image

import com.example.invitation.domain.file.*
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL
import java.nio.file.Files
import javax.imageio.ImageIO


interface CardImageService {
    fun createImage(cardImageCreateVo: CardImageCreateVo): File
}

@Service
class CardImageServiceImpl(
    private val storageService: StorageService,
    private val fileService: FileService,
) : CardImageService {
    override fun createImage(cardImageCreateVo: CardImageCreateVo): File {
        // 이미지 처리
        val background = ImageIO.read(
            URL(
                cardImageCreateVo.cardTemplate.imageUrl
            )
        )
        val assets = cardImageCreateVo.cardTemplateItems.map { Triple(ImageIO.read(URL(it.imageUrl)), it.x, it.y) }
        val g2d = background.createGraphics()
        assets.forEach { g2d.drawImage(it.first, it.second, it.third, null) }

        val outputFile = Files.createTempFile("card", ".jpg").toFile()
        ImageIO.write(background, "jpg", outputFile)
        g2d.dispose()

        // 파일 업로드
        return saveFile(
            inputStream = FileInputStream(outputFile),
            contentType = "image/jpeg",
            size = outputFile.length(),
        )
    }

    private fun saveFile(
        inputStream: InputStream,
        contentType: String,
        size: Long
    ): File {
        return storageService.upload(
            inputStream = inputStream,
            storageUploadRequestVo = StorageUploadRequestVo(
                contentType = contentType,
                size = size,
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
        }
    }
}
