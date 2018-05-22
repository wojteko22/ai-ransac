import kotlin.system.measureTimeMillis

private val io = FileHelper()
private const val resultPathname = "result2.json"

fun main(args: Array<String>) {
    val millis = measureTimeMillis {
        savePairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift")
        readPairs()
    }
    println("millis: $millis")
}

private fun readPairs() {
    val keyPointsPairs = io.keyPointsPairs(resultPathname)!!
    println(keyPointsPairs[0].first)
}

private fun savePairs(fileName1: String, fileName2: String) {
    val picture1 = io.keyPoints(fileName1)
    val picture2 = io.keyPoints(fileName2)
    val pairs = picture1.keyPointsPairs(picture2)
    io.save(resultPathname, pairs)
}
