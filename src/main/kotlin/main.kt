const val featuresCount = 128

fun main(args: Array<String>) {
    val firstFileName = "DSC_5824.png.haraff.sift"
    val secondFileName = "DSC_5825.png.haraff.sift"
    val reader = Reader("src/main/resources")
    val keyPoints1 = reader.keyPoints(firstFileName)
    val keyPoints2 = reader.keyPoints(secondFileName)
    println("$firstFileName $secondFileName")
    val indexes1 = keyPoints1.map { findClosestIndex(it, keyPoints2) }
    val indexes2 = keyPoints2.map { findClosestIndex(it, keyPoints1) }
    indexes1.forEachIndexed { index, indexOfClosest ->
        if (indexes2[indexOfClosest] == index) {
            println("$index $indexOfClosest")
        }
    }
}

fun findClosestIndex(point: KeyPoint, keyPoints: List<KeyPoint>): Int {
    val distances = keyPoints.map {
        distance(point, it)
    }
    val minDistance = distances.min()
    return distances.indexOf(minDistance)
}

fun distance(point1: KeyPoint, point2: KeyPoint): Double {
    val sum = (0 until featuresCount).map {
        val difference = point1.features[it] - point2.features[it]
        Math.pow(difference.toDouble(), 2.0)
    }.sum()
    return Math.sqrt(sum)
}
