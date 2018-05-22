import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val millis = measureTimeMillis {
        printPairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift")
    }
    println("millis: $millis")
}

private fun printPairs(fileName1: String, fileName2: String) {
    val reader = Reader()
    val picture1 = reader.keyPoints(fileName1)
    val picture2 = reader.keyPoints(fileName2)
    println("$fileName1 $fileName2")
    picture1.keyPointsPairs(picture2).forEach {
        println("${it.first} ${it.second}")
    }
}
