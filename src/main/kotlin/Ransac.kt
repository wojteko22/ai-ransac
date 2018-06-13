import org.ejml.simple.SimpleMatrix
import transform.Transform
import transform.AffineTransform

class Ransac(private val transform: Transform = AffineTransform()) {

    fun filterWithRansac(
        pairs: List<Pair<Point, Point>>,
        maxError: Int,
        iterationsCount: Int
    ): List<Pair<Point, Point>> {
        val model = bestModel(pairs, maxError, iterationsCount)
        return pairs.filter {
            modelError(model, it) < maxError
        }
    }

    fun bestModel(pairs: List<Pair<Point, Point>>, maxError: Int, iterationsCount: Int): SimpleMatrix {
        var bestModel: SimpleMatrix? = null
        var bestScore = 0
        repeat(iterationsCount) {
            var model: SimpleMatrix? = null
            while (model == null) {
                model = transform.model(pairs)
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
        return bestModel!!
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
        val t = resultMatrix[2]
        val u = resultMatrix[0] / t
        val v = resultMatrix[1] / t
        val realU = data.second.x
        val realV = data.second.y
        val distanceSquare = Math.pow(u - realU, 2.0) + Math.pow(v - realV, 2.0)
        return Math.sqrt(distanceSquare)
    }
}
