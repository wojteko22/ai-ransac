object VerySimpleHeuristics : Heuristics {

    override fun selectedPairs(pairs: List<Pair<Point, Point>>): List<Pair<Point, Point>> = pairs.shuffled()
}
