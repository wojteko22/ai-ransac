import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

private const val neighborhoodSize = 4
private const val threshold = 0.6
private const val pairsPathname = "pairs2.json"
private const val consistentPairsPathname = "consistent2,n=$neighborhoodSize,t=$threshold.json"

private const val resourcesPathname = "src/main/resources"
private val io = PairsIOExecutor(resourcesPathname)

fun main(args: Array<String>) {
    doSomethingWithPairs()
    doSomethingWithImages()
}

private fun doSomethingWithPairs() {
    io.savePairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift", pairsPathname)
    io.saveConsistentPairs(pairsPathname, consistentPairsPathname, neighborhoodSize, threshold)
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
