import java.io.File

const val featuresCount = 128

fun main(args: Array<String>) {
    val rootPath = "src/main/resources"
    val firstFileName = "DSC_5824"
    val secondFileName = "DSC_5825"
    val keyPoints1 = keyPoints("$rootPath/$firstFileName.png.haraff.sift")
    val keyPoints2 = keyPoints("$rootPath/$secondFileName.png.haraff.sift")
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

private fun keyPoints(pathname: String): List<KeyPoint> {
    val file = File(pathname)
    val lines = file.readLines()
    val subList = lines.subList(2, lines.size)
    return subList.map {
        val itemsInLine = it.split(' ')
        val x = itemsInLine[0].toDouble()
        val y = itemsInLine[1].toDouble()
        val features = itemsInLine.subList(5, itemsInLine.size).map { it.toInt() }
        KeyPoint(x, y, features)
    }
}

class KeyPoint(val x: Double, val y: Double, val features: List<Int>)