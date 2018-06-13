class SimpleHeuristics(r: Double, R: Double) : Heuristics {

    private val rSquare = Math.pow(r, 2.0)
    private val RSquare = Math.pow(R, 2.0)

    override fun selectedPairs(pairs: List<Pair<Point, Point>>, pointsCount: Int): List<Pair<Point, Point>> {
        val selectedPairs = mutableListOf<Pair<Point, Point>>()
        val shuffled = pairs.shuffled()
        selectedPairs += shuffled[0]
        var index = 1
        while (index < shuffled.size && selectedPairs.size < pointsCount) {
            val pair = shuffled[index]
            val ok = ok(pair, selectedPairs)
            if (ok) {
                selectedPairs += pair
            }
            index++
        }
        return selectedPairs
    }

    private fun ok(pair: Pair<Point, Point>, selectedPairs: MutableList<Pair<Point, Point>>): Boolean =
        selectedPairs.all {
            ok(it.first, pair.first) && ok(it.second, pair.second)
        }

    private fun ok(firstPoint: Point, secondPoint: Point): Boolean {
        val distanceSquare = firstPoint.distanceSquare(secondPoint)
        return rSquare < distanceSquare && distanceSquare < RSquare
    }
}
