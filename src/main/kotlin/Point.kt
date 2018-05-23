data class Point(val x: Double, val y: Double) {

    fun distanceSquare(point: Point) = Math.pow(x - point.x, 2.0) + Math.pow(y - point.y, 2.0)
}
