package heuristics

import Point

object VerySimpleHeuristics : Heuristics {

    override fun selectedPairs(pairs: List<Pair<Point, Point>>, pointsCount: Int): List<Pair<Point, Point>> =
        pairs.shuffled()
}
