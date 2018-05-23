object PairsProcessor {

    fun consistentPairs(
        pairs: List<Pair<Point, Point>>,
        neighborhoodSize: Int,
        threshold: Double
    ): List<Pair<Point, Point>> = pairs.filter {
        val neighboursOfFirstWithFriends = neighborsWithFriends(it.first, pairs - it, neighborhoodSize)
        val neighboursOfSecond = neighbors(it.second, (pairs - it).map { it.second }, neighborhoodSize)
        val consistentNeighboursCount = neighboursOfFirstWithFriends.count {
            neighboursOfSecond.contains(it.second)
        }
        consistentNeighboursCount.toDouble() / neighborhoodSize > threshold
    }

    private fun neighborsWithFriends(
        point: Point,
        pairs: List<Pair<Point, Point>>,
        neighborhoodSize: Int
    ): List<Pair<Point, Point>> {
        return pairs.map {
            val distance = point.distanceSquare(it.first)
            Pair(it, distance)
        }.sortedBy { it.second }.take(neighborhoodSize).map { it.first }
    }

    private fun neighbors(
        point: Point,
        pairs: List<Point>,
        neighborhoodSize: Int
    ): List<Point> {
        return pairs.map {
            val distance = point.distanceSquare(it)
            Pair(it, distance)
        }.sortedBy { it.second }.take(neighborhoodSize).map { it.first }
    }
}
