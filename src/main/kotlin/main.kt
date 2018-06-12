private val names = listOf("kaczka", "kubek", "muza", "mysz", "radek")

private const val neighborhoodSize = 5
private const val threshold = 0.6
private val pairsFileName = names[4]
private const val r = 7.0
private const val R = 220.0
private const val maxError = 30
private const val iterationsCount = 20

private val pairsPath = "all/$pairsFileName"
private val image1 = "${pairsFileName}1.png"
private val image2 = "${pairsFileName}2.png"
private val consistentPairsPath = "consistent/$pairsFileName,n=$neighborhoodSize,t=$threshold"
private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
//    executor.savePairs("haraff/$image1", "haraff/$image2", pairsPath)
    executor.saveConsistentPairs(pairsPath, consistentPairsPath, neighborhoodSize, threshold)
    executor.countPairs(consistentPairsPath)
    drawSomething(consistentPairsPath)
//    useRansacWithVerySimpleHeuristics()
//    useRansacWithSimpleHeuristics(r, R)
}

private fun drawSomething(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", pairsPath)
}

private fun useRansacWithVerySimpleHeuristics() {
    val heuristics = VerySimpleHeuristics()
    val pairsPath =  useRansac(heuristics)
    drawRansacLines(pairsPath)
}

private fun useRansacWithSimpleHeuristics(r: Double, R: Double) {
    val heuristics = SimpleHeuristics(r, R)
    val pairsPath = useRansac(heuristics, ",r=$r,R=$R")
    drawRansacLines(pairsPath)
}

private fun useRansac(heuristics: Heuristics, pathSuffix: String = ""): String {
    val destPath = "${pairsFileName}_m=$maxError,i=$iterationsCount$pathSuffix"
    executor.useRansac(pairsPath, maxError, iterationsCount, "ransac/$destPath", heuristics)
    return destPath
}

private fun drawRansacLines(pairsPath: String) {
    executor.drawLines("images/$image1", "images/$image2", "ransac/$pairsPath")
}
