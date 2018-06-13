package transform

import org.ejml.simple.SimpleMatrix
import Point
import Heuristics

abstract class Transform {

    abstract fun model(pairs: List<Pair<Point, Point>>, heuristics: Heuristics): SimpleMatrix?

    protected fun model(firstMatrix: SimpleMatrix, secondMatrix: SimpleMatrix): SimpleMatrix? =
        try {
            val resultOfMultiplication = firstMatrix.invert().mult(secondMatrix)
            model(resultOfMultiplication)
        } catch (e: Exception) {
            null
        }

    protected abstract fun model(vector: SimpleMatrix): SimpleMatrix
}
