import kotlin.system.measureTimeMillis

private const val pairsPathname = "pairs2.json"

fun main(args: Array<String>) {
    val io = FileHelper()
    val millis = measureTimeMillis {
//        io.savePairs("DSC_5824.png.haraff.sift", "DSC_5825.png.haraff.sift", pairsPathname)
        io.saveConsistentPairs(pairsPathname, "consistent2.json", 4, 0.6)
    }
    println("millis: $millis")
}
