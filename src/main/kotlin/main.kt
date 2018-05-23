import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

private const val neighborhoodSize = 4
private const val threshold = 0.6
private const val pairsPathname = "pairs2.json"
private const val image1 = "DSC_5824.png"
private const val image2 = "DSC_5825.png"
private const val resultImage = "my.png"
private const val consistentPairsPathname = "consistent2,n=$neighborhoodSize,t=$threshold.json"

private const val resourcesPathname = "src/main/resources"
private val executor = PairsIOExecutor(resourcesPathname)
private val io = FileHelper(resourcesPathname)

fun main(args: Array<String>) {
    doSomethingWithPairs()
    drawLines(image1, image2, resultImage)
}

private fun doSomethingWithPairs() {
    executor.savePairs("$image1.haraff.sift", "$image2.haraff.sift", pairsPathname)
    executor.saveConsistentPairs(pairsPathname, consistentPairsPathname, neighborhoodSize, threshold)
}

private fun drawLines(pathname1: String, pathname2: String, resultPathname: String) {
    val firstImage = io.image(pathname1)
    val secondImage = io.image(pathname2)
    val newImage = BufferedImage(firstImage.width, firstImage.height + secondImage.height, BufferedImage.TYPE_INT_ARGB)
    with(newImage.graphics) {
        drawImage(firstImage, 0, 0, null)
        drawImage(secondImage, 0, firstImage.height, null)
        color = Color.RED
        val pairs = io.pointsPairs(consistentPairsPathname)
        pairs.forEach {
            val firstPoint = it.first
            val secondPoint = it.second
            drawLine(
                firstPoint.x.roundToInt(),
                firstPoint.y.roundToInt(),
                secondPoint.x.roundToInt(),
                firstImage.height + secondPoint.y.roundToInt()
            )
        }
    }
    io.saveImage(newImage, resultPathname)
}
