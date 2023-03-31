package com.example.invitation.domain.card.image

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.time.LocalDateTime
import javax.imageio.ImageIO

@Disabled
@SpringBootTest
class CardImageServiceImplTest {
    @Test
    fun readImageFromNetworkAndWriteNewImage() {
        // 배경 읽기
        val background = ImageIO.read(URL("http://localhost:8080/api/v1/files/3"))
        // 토끼 읽기
        val overlay1 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay2 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay3 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay4 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay5 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay6 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay7 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay8 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))
        val overlay9 = ImageIO.read(URL("http://localhost:8080/api/v1/files/7"))

        val g2d = background.createGraphics()
        g2d.drawImage(overlay1, 0, 0, null)
        g2d.drawImage(overlay2, 360, 0, null)
        g2d.drawImage(overlay3, 720, 0, null)
        g2d.drawImage(overlay4, 0, 360, null)
//        g2d.drawImage(overlay5, 360, 360, null)
        g2d.drawImage(overlay6, 720, 360, null)
        g2d.drawImage(overlay7, 0, 720, null)
        g2d.drawImage(overlay8, 360, 720, null)
        g2d.drawImage(overlay9, 720, 720, null)

        val outputFile = File(Files.createTempFile("output_", ".jpg").toUri())
            .apply { deleteOnExit() }

        ImageIO.write(background, "jpg", outputFile)
        outputFile.copyTo(File("/Users/Dobby/Downloads/output_${LocalDateTime.now()}.jpg"), true)

//        val readBytes = outputFile.readBytes()
//        val upload = storageService.upload(
//            inputStream = ByteArrayInputStream(readBytes),
//            storageUploadRequestVo = StorageUploadRequestVo(
//                contentType = "image/jpeg",
//                size = readBytes.size.toLong(),
//            ),
//        )
//        println(upload)

        // Dispose of the Graphics2D object
        g2d.dispose()
    }
}
