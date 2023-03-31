package com.example.invitation.ui

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.awt.*
import java.awt.font.TextLayout
import java.awt.geom.AffineTransform
import java.awt.image.*
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JLabel


@Disabled
class ImageIOTest {
    @Test
    fun name() {
        try {
            // Load image file
            val image: BufferedImage = ImageIO.read(File("/Users/Dobby/Downloads/potenday.png"))

            // Get graphics object from image
            val graphics = image.graphics

            // Set font and color for text
            val font = Font("Arial", Font.BOLD, 24)
            val color = Color.RED
            graphics.font = font
            graphics.color = color

            // Add text to image
            graphics.drawString("Sample Text", 10, 30)

            // Save modified image to file
            ImageIO.write(image, "jpg", File("/Users/Dobby/Downloads/output_${LocalDateTime.now()}.jpg"))

            // Dispose graphics object
            graphics.dispose()
            println("Image saved successfully.")
        } catch (e: Exception) {
            println("Error: " + e.message)
        }
    }

    @Test
    fun test() {
        val f = JFrame()
        f.add(ShadowText())
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.setSize(450, 150)

        f.isVisible = true
        Thread.sleep(100000)
    }

    @Test
    fun testImageOverAnotherImage() {
        try {
            // Load the background image
            val background = ImageIO.read(File("/Users/Dobby/Downloads/potenday.png"))

            // Load the image to be overlaid
            val overlay = ImageIO.read(File("/Users/Dobby/Downloads/test.png"))

            // Create a Graphics2D object from the background image
            val g2d = background.createGraphics()

            // Draw the overlay image onto the background image
            g2d.drawImage(overlay, 0, 0, null)

            // Save the modified background image to a new file
            val outputfile = File("/Users/Dobby/Downloads/output_${LocalDateTime.now()}.jpg")

            ImageIO.write(background, "jpg", outputfile)

            // Dispose of the Graphics2D object
            g2d.dispose()
            println("Images overlapped successfully.")
        } catch (e: IOException) {
            println("Error: " + e.message)
        }
    }

    class ShadowText : JLabel() {
        override fun paint(g: Graphics) {
            val text = "Hello World"
            val x = 10.toFloat()
            val y = 100.toFloat()

            val font = Font("Georgia", Font.ITALIC, 50);
            val g1 = g as Graphics2D

            val textLayout = TextLayout(text, font, g1.fontRenderContext);
            g1.paint = Color(150, 150, 150)
            textLayout.draw(g1, x + 1, y + 1)

            g1.paint = Color.BLACK
            textLayout.draw(g1, x, y)
        }
    }

    @Test
    fun testBlur() {
        val f = JFrame()
        f.add(Blur())
        f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        f.setSize(450, 150)

        f.isVisible = true
        Thread.sleep(100000)
    }

    class Blur : JLabel() {
        var bi: BufferedImage
        val elements: List<Float> = listOf(
            .1111f, .1111f, .1111f,
            .1111f, .1111f, .1111f,
            .1111f, .1111f, .1111f
        )

        init {
            background = Color.white

            val img = toolkit.getImage("/Users/Dobby/Downloads/potenday.png")
            try {
                val tracker = MediaTracker(this)
                tracker.addImage(img, 0)
                tracker.waitForID(0)
            } catch (e: Exception) {
            }

            val iw = img.getWidth(this)
            val ih = img.getHeight(this)
            bi = BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB)
            val big = bi.createGraphics()
            big.drawImage(img, 0, 0, this)
        }

        override fun print(g: Graphics) {
            val g2 = g as Graphics2D
            val w = size.width
            val h = size.height
            val bw = bi.getWidth(this)
            val bh = bi.getHeight(this)

            val at = AffineTransform()
            at.scale(w / 2.0 / bw, h / 1.0 / bh)

            var biop: BufferedImageOp? = null
            val bimg = BufferedImage(bw, bh, BufferedImage.TYPE_INT_RGB)

            val kernel: Kernel = Kernel(3, 3, elements.toFloatArray())
            val cop = ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null)
            cop.filter(bi, bimg)
            biop = AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

            g2.drawImage(bi, biop, 0, 0);
            g2.drawImage(bimg, biop, w / 2 + 3, 0);
        }
    }

    @Test
    fun blur() {
        // Load an image
        val image = ImageIO.read(File("/Users/Dobby/Downloads/output.jpg"))

        // Create a blur kernel
        val blurRadius = 10
        val kernelSize = blurRadius * 2 + 1
        val kernelData = FloatArray(kernelSize * kernelSize)
        val kernelWeight = 1.0f / (kernelSize * kernelSize)
        for (i in kernelData.indices) {
            kernelData[i] = kernelWeight
        }
        val kernel = Kernel(kernelSize, kernelSize, kernelData)

        // Create a ConvolveOp with the blur kernel
        val op = ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null)

        // Apply the ConvolveOp to the image
        val blurredImage = BufferedImage(image.width, image.height, image.type)
        val g2d = blurredImage.createGraphics()
        g2d.drawImage(image, op, 0, 0)

        // Save modified image to file
        ImageIO.write(image, "jpg", File("/Users/Dobby/Downloads/output_${LocalDateTime.now()}.jpg"))

        g2d.dispose()
    }
}
