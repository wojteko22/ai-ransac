import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking

data class Picture(private val keyPoints: List<KeyPoint>) {

    fun keyPointsPairs(picture: Picture): List<Pair<KeyPoint, KeyPoint>> = indexesPairs(picture).map {
        val first = keyPoints[it.first]
        val second = picture.keyPoints[it.second]
        Pair(first, second)
    }

    private fun indexesPairs(picture: Picture): List<Pair<Int, Int>> = runBlocking {
        val indexes1 = respectivelyClosestIndexesIn(picture)
        val indexes2 = picture.respectivelyClosestIndexesIn(this@Picture)
        indexes1.mapIndexedNotNull { index, indexOfClosest ->
            pairOrNull(indexes2, indexOfClosest, index)
        }
    }

    private fun pairOrNull(indexes2: List<Int>, indexOfClosest: Int, index: Int): Pair<Int, Int>? {
        return if (indexes2[indexOfClosest] == index) {
            Pair(index, indexOfClosest)
        } else {
            null
        }
    }

    private suspend fun respectivelyClosestIndexesIn(picture: Picture) = keyPoints.map {
        async {
            findClosestIndex(it, picture.keyPoints)
        }
    }.map { it.await() }

    private fun findClosestIndex(point: KeyPoint, keyPoints: List<KeyPoint>): Int {
        val distances = keyPoints.map {
            distance(point, it)
        }
        val minDistance = distances.min()
        return distances.indexOf(minDistance)
    }

    private fun distance(point1: KeyPoint, point2: KeyPoint): Double {
        val sum = (0 until point1.size).map {
            val difference = point1.features[it] - point2.features[it]
            Math.pow(difference.toDouble(), 2.0)
        }.sum()
        return Math.sqrt(sum)
    }
}
