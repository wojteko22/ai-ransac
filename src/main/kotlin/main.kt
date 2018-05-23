import org.ejml.data.DMatrixRMaj
import org.ejml.simple.SimpleMatrix

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
//    executor.saveConsistentPairs(pairs, consistentPairsDescription, neighborhoodSize, threshold)
//    executor.drawLines(image1, image2, consistentPairsDescription)

    val pointsPairs = io.pointsPairs("$pairs.json")
    affineTransform(pointsPairs)

}

private val io = FileHelper(resourcesPathname)

//fun ransac(pairs: List<Pair<Point, Point>>) {
//
//    var bestmodel = null
//    var bestscore = 0
//    val iter = 0..2
//
//    for (i in iter) {
//        var model = null
//        while (model == null) {
//            val s = randomlyChooseNSamplesFromD
//            model = calculateModel(s)
//        }
//        var score = 0
//        for (data in D) {
//            var error = modelerror(model, data)
//            if (error < maxerror) {
//                score += 1
//            }
//        }
//        if (score > bestscore) {
//            bestscore = score
//            bestmodel = model
//        }
//    }
//    return bestmodel
//
//}

fun affineTransform(pairs: List<Pair<Point, Point>>) {
    val point1OfFirst = pairs[0].first
    val point2OfFirst = pairs[1].first
    val point3OfFirst = pairs[2].first
    val point1OfSecond = pairs[0].second
    val point2OfSecond = pairs[1].second
    val point3OfSecond = pairs[2].second
    val first = SimpleMatrix(
        6, 6, true,
        doubleArrayOf(
            point1OfFirst.x, point1OfFirst.y, 1.0, 0.0, 0.0, 0.0,
            point2OfFirst.x, point2OfFirst.y, 1.0, 0.0, 0.0, 0.0,
            point3OfFirst.x, point3OfFirst.y, 1.0, 0.0, 0.0, 0.0,
            0.0, 0.0, 0.0, point1OfFirst.x, point1OfFirst.y, 1.0,
            0.0, 0.0, 0.0, point2OfFirst.x, point2OfFirst.y, 1.0,
            0.0, 0.0, 0.0, point3OfFirst.x, point3OfFirst.y, 1.0
        )
    )
    println(first)
    val second = DMatrixRMaj(
        6, 1, true,
        point1OfSecond.x,
        point2OfSecond.x,
        point3OfSecond.x,
        point1OfSecond.y,
        point2OfSecond.y,
        point3OfSecond.y
    )
}
