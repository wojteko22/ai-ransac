import kotlin.system.measureTimeMillis

private val names = listOf("kaczka", "kubek", "muza", "mysz", "radek")

private const val neighborhoodSize = 5
private const val threshold = 0.6
private val pairsFileName = names[1]
private const val r = 4.0
private const val R = 240.0
private const val maxError = 20
private const val iterationsCount = 1000

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
//    measure { useRansacWithVerySimpleHeuristics() }
    measure { useRansacWithSimpleHeuristics(r, R) }
}

private fun measure(function: () -> Unit) {
    val millis = measureTimeMillis { function() }
    println("millis: $millis")
}

private fun drawSomething(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", pairsPath)
}

private fun useRansacWithVerySimpleHeuristics() {
    val pairsPath = useRansac(VerySimpleHeuristics)
    drawRansacLines(pairsPath)
}

private fun useRansacWithSimpleHeuristics(r: Double, R: Double) {
    val heuristics = SimpleHeuristics(r, R)
    val pairsPath = useRansac(heuristics, ",r=$r,R=$R")
    drawRansacLines(pairsPath)
}

private fun useRansac(heuristics: Heuristics, pathSuffix: String = ""): String {
    val destPath = "ransac/${pairsFileName}_m=$maxError,i=$iterationsCount$pathSuffix"
    val ransac = Ransac(heuristics = heuristics)
    executor.useRansac(pairsPath, maxError, iterationsCount, destPath, ransac)
    return destPath
}

private fun drawRansacLines(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", pairsPath)
}
