import kotlin.system.measureTimeMillis

private val names = listOf("kaczka", "kubek", "muza", "mysz", "radek")

private val pairsFileName = names[4]

private const val neighborhoodSize = 5
private const val threshold = 0.6

private const val maxError = 20
private const val iterationsCount = 1000

private const val r = 4.0
private const val R = 700.0

//private val heuristics: Heuristics = SimpleHeuristics(r, R)
private val heuristics: Heuristics = VerySimpleHeuristics

//private val transform = AffineTransform(heuristics)
private val transform = PerspectiveTransform(heuristics)

private val pairsPath = "all/$pairsFileName"
private val image1 = "${pairsFileName}1.png"
private val image2 = "${pairsFileName}2.png"
private val consistentPairsPath = "consistent/$pairsFileName,n=$neighborhoodSize,t=$threshold"
private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
//    executor.savePairs("haraff/$image1", "haraff/$image2", pairsPath)
//    executor.saveConsistentPairs(pairsPath, consistentPairsPath, neighborhoodSize, threshold)
//    executor.countPairs(consistentPairsPath)
//    drawSomething(consistentPairsPath)
    measure { makeRansacImage() }
}

private fun measure(function: () -> Unit) {
    val millis = measureTimeMillis { function() }
    println("Millis: $millis")
}

private fun drawSomething(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", pairsPath)
}

private fun makeRansacImage() {
    val pathSuffix = if (heuristics is SimpleHeuristics) ",r=$r,R=$R" else ""
    val destPath = "ransac/${pairsFileName}_m=$maxError,i=$iterationsCount$pathSuffix"
    val ransac = Ransac(transform)
    executor.useRansac(pairsPath, maxError, iterationsCount, destPath, ransac)
    executor.drawLines("images/$image1", "images/$image2", destPath)
}
