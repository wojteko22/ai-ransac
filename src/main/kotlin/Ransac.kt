import org.ejml.simple.SimpleMatrix

object Ransac {

    fun bestModel(pairs: List<Pair<Point, Point>>, maxError: Int, iterationsCount: Int): SimpleMatrix? {
        var bestModel: SimpleMatrix? = null
        var bestScore = 0
        repeat(iterationsCount) {
            var model: SimpleMatrix? = null
            while (model == null) {
                model = affineTransform(pairs)
            }
            var score = 0
            for (data in pairs) {
                val error = modelError(model, data)
                if (error < maxError) {
                    score += 1
                }
            }
            if (score > bestScore) {
                bestScore = score
                bestModel = model
            }
        }
        return bestModel
    }

    private fun affineTransform(pairs: List<Pair<Point, Point>>): SimpleMatrix? {
        val shuffledPairs = pairs.shuffled()
        val point1OfFirst = shuffledPairs[0].first
        val point2OfFirst = shuffledPairs[1].first
        val point3OfFirst = shuffledPairs[2].first
        val point1OfSecond = shuffledPairs[0].second
        val point2OfSecond = shuffledPairs[1].second
        val point3OfSecond = shuffledPairs[2].second
        val firstMatrix = SimpleMatrix(
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
        val secondMatrix = SimpleMatrix(
            6, 1, true,
            doubleArrayOf(
                point1OfSecond.x,
                point2OfSecond.x,
                point3OfSecond.x,
                point1OfSecond.y,
                point2OfSecond.y,
                point3OfSecond.y
            )
        )
        return affineTransform(firstMatrix, secondMatrix)
    }

    private fun affineTransform(firstMatrix: SimpleMatrix, secondMatrix: SimpleMatrix): SimpleMatrix? =
        try {
            val resultOfMultiplication = firstMatrix.invert().mult(secondMatrix)
            affineTransform(resultOfMultiplication)
        } catch (e: Exception) {
            null
        }

    private fun affineTransform(vector: SimpleMatrix): SimpleMatrix {
        val a = vector[0]
        val b = vector[1]
        val c = vector[2]
        val d = vector[3]
        val e = vector[4]
        val f = vector[5]
        return SimpleMatrix(
            3, 3, true,
            doubleArrayOf(
                a, b, c,
                d, e, f,
                0.0, 0.0, 1.0
            )
        )
    }

    private fun modelError(model: SimpleMatrix, data: Pair<Point, Point>): Double {
        val x = data.first.x
        val y = data.first.y
        val secondMatrix = SimpleMatrix(
            3, 1, true,
            doubleArrayOf(
                x,
                y,
                1.0
            )
        )
        val resultMatrix = model.mult(secondMatrix)
        val u = resultMatrix[0]
        val v = resultMatrix[1]
        val realU = data.second.x
        val realV = data.second.y
        val distanceSquare = Math.pow(u - realU, 2.0) + Math.pow(v - realV, 2.0)
        return Math.sqrt(distanceSquare)
    }
}
