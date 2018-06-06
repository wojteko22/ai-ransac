private val names = listOf("kaczka", "kubek", "muza", "mysz", "radek")

private const val neighborhoodSize = 5
private const val threshold = 0.6
private val pairsFileName = names[1]

private val pairsPath = "all/$pairsFileName"
private val image1 = "${pairsFileName}1.png"
private val image2 = "${pairsFileName}2.png"
private val consistentPairsPath = "consistent/$pairsFileName,n=$neighborhoodSize,t=$threshold"
private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
    executor.savePairs("haraff/$image1", "haraff/$image2", pairsPath)
    executor.saveConsistentPairs(pairsPath, consistentPairsPath, neighborhoodSize, threshold)
    executor.drawLines("images/$image1", "images/$image2", pairsPath, "visualization/$pairsFileName")
    executor.testRansac(pairsFileName, 30, 3)
}
