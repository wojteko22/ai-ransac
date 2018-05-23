private const val neighborhoodSize = 5
private const val threshold = 0.8
private const val pairs = "all2"
private const val image1 = "DSC_5824.png"
private const val image2 = "DSC_5825.png"
private const val consistentPairsDescription = "consistent2,n=$neighborhoodSize,t=$threshold"

private const val resourcesPathname = "src/main/resources"
private val executor = OperationExecutor(resourcesPathname)

fun main(args: Array<String>) {
//    executor.savePairs("$image1.haraff.sift", "$image2.haraff.sift", pairs)
    executor.saveConsistentPairs(pairs, consistentPairsDescription, neighborhoodSize, threshold)
    executor.drawLines(image1, image2, consistentPairsDescription)
}

//fun ransac(pairs: List<Pair<Point, Point>>) {
//
//    var bestmodel = null
//    var bescore = 0
//    val iter = 0..2
//
//    for (i in iter) {
//        var model = null
//        while (model == null) {
//            val s = randomlyChooseNSamplesFromD
//            model = calculatemodel(s)
//        }
//        var score = 0
//        for (data in D) {
//            val
//        }
//    }
//
//}
