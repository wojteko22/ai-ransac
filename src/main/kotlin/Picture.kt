data class Picture(private val keyPoints: List<KeyPoint>) {

    fun respectivelyClosestIndexesIn(picture: Picture) = keyPoints.map {
        findClosestIndex(it, picture.keyPoints)
    }

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
