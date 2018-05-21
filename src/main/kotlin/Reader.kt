import java.io.File

class Reader {
    fun keyPoints(pathname: String): List<KeyPoint> {
        val file = File(pathname)
        val lines = file.readLines()
        val subList = lines.subList(2, lines.size)
        return subList.map {
            val itemsInLine = it.split(' ')
            val x = itemsInLine[0].toDouble()
            val y = itemsInLine[1].toDouble()
            val features = itemsInLine.subList(5, itemsInLine.size).map { it.toInt() }
            KeyPoint(x, y, features)
        }
    }
}
