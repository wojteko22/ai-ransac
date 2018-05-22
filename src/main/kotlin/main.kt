import kotlin.system.measureTimeMillis

private const val resultPathname = "result2.json"

fun main(args: Array<String>) {
    val io = FileHelper()
    val millis = measureTimeMillis {
        io.savePairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift", resultPathname)
        io.readPairs(resultPathname)
    }
    println("millis: $millis")
}
