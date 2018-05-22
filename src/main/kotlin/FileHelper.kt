import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.io.FileReader
import java.io.FileWriter


class FileHelper(private val rootPathname: String = "src/main/resources") {

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

    fun save(pathname: String, toSave: List<Any?>) {
        val fileWriter = FileWriter("$rootPathname/$pathname")
        val gson = GsonBuilder().setPrettyPrinting().create()
        val writer = gson.newJsonWriter(fileWriter)
        writer.beginArray()
        for (element in toSave) {
            gson.toJson(element, element!!::class.java, writer)
        }
        writer.endArray()
        writer.close()
    }

    fun pointsPairs(pathname: String): List<Pair<Point, Point>>? {
        val reader = FileReader("$rootPathname/$pathname")
        return Gson().fromJson(reader)
    }
}