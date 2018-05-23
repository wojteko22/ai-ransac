import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.system.measureTimeMillis

private const val pairsPathname = "pairs2.json"
private const val resourcesPathname = "src/main/resources"

fun main(args: Array<String>) {
    doSomethingWithPairs()
    doSomethingWithImages()
}

private fun doSomethingWithPairs() {
    val io = PairsIOExecutor(resourcesPathname)
    val millis = measureTimeMillis {
        io.savePairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift", pairsPathname)
        io.saveConsistentPairs(pairsPathname, "consistent2.json", 4, 0.6)
    }
    println("millis: $millis")
}

private fun doSomethingWithImages() {
    val srcImage = ImageIO.read(File("$resourcesPathname/DSC_5824.png"))
    val newImage = BufferedImage(srcImage.width, srcImage.height, BufferedImage.TYPE_INT_ARGB)
    with(newImage.graphics) {
        drawImage(srcImage, 0, 0, null)
        color = Color.RED
        fillOval(0, 0, 300, 300)
    }
    ImageIO.write(newImage, "png", File("$resourcesPathname/my.png"))
}
