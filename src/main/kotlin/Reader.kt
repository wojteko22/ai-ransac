import java.io.File

class Reader(private val rootPathname: String) {

    fun keyPoints(pathname: String): Picture {
        val file = File("$rootPathname/$pathname")
        val lines = file.readLines()
        val subList = lines.subList(2, lines.size)
        val keyPoints = subList.map {
            val itemsInLine = it.split(' ')
            val x = itemsInLine[0].toDouble()
            val y = itemsInLine[1].toDouble()
            val features = itemsInLine.subList(5, itemsInLine.size).map { it.toInt() }
            KeyPoint(x, y, features)
        }
        return Picture(keyPoints)
    }
}
