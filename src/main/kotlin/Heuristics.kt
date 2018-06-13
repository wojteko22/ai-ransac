interface Heuristics {
    fun selectedPairs(pairs: List<Pair<Point, Point>>, pointsCount: Int): List<Pair<Point, Point>>
}
