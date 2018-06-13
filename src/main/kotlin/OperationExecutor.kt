import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

class OperationExecutor(rootPathname: String) {

    private val io = FileHelper(rootPathname)

    fun saveConsistentPairs(pairsFileName: String, destFileName: String, neighborhoodSize: Int, threshold: Double) {
        val pairs = io.pointsPairs("$pairsFileName.json")
        val consistentPairs = PairsProcessor.consistentPairs(pairs, neighborhoodSize, threshold)
        io.save("$destFileName.json", consistentPairs)
    }

    fun countPairs(pairsFileName: String) {
        val pairs = io.pointsPairs("$pairsFileName.json")
        println(pairs.size)
    }

    fun savePairs(fileName1: String, fileName2: String, resultFileName: String) {
        val picture1 = io.keyPoints(fileName1)
        val picture2 = io.keyPoints(fileName2)
        val pairs = picture1.keyPointsPairs(picture2)
        io.save("$resultFileName.json", pairs)
    }

    fun drawLines(image1Pathname: String, image2Pathname: String, pairsFileName: String) {
        val firstImage = io.image(image1Pathname)
        val secondImage = io.image(image2Pathname)
        val newImage =
            BufferedImage(firstImage.width, firstImage.height + secondImage.height, BufferedImage.TYPE_INT_ARGB)
        with(newImage.graphics) {
            drawImage(firstImage, 0, 0, null)
            drawImage(secondImage, 0, firstImage.height, null)
            color = Color.RED
            val pairs = io.pointsPairs("$pairsFileName.json")
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
        io.saveImage(newImage, "$pairsFileName.png")
    }

    fun useRansac(
        pairsFileName: String,
        maxError: Int,
        iterationsCount: Int,
        destPath: String,
        ransac: Ransac
    ) {
        val pointsPairs = io.pointsPairs("$pairsFileName.json")
        val newPairs = ransac.filterWithRansac(pointsPairs, maxError, iterationsCount)
        println(newPairs.size)
        io.saveJson(destPath, newPairs)
    }
}
