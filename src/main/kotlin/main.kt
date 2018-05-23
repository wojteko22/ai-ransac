private const val neighborhoodSize = 4
private const val threshold = 0.6
private const val pairsPathname = "pairs2.json"
private const val image1 = "DSC_5824.png"
private const val image2 = "DSC_5825.png"
private const val consistentPairsDescription = "consistent2,n=$neighborhoodSize,t=$threshold"
private const val consistentPairsPathname = "$consistentPairsDescription.json"
private const val resultImage = "$consistentPairsDescription.png"

private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
//    executor.savePairs("$image1.haraff.sift", "$image2.haraff.sift", pairsPathname)
//    executor.saveConsistentPairs(pairsPathname, consistentPairsPathname, neighborhoodSize, threshold)
    executor.drawLines(image1, image2, resultImage, consistentPairsPathname)
}
