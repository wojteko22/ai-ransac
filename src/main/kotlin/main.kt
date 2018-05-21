const val featuresCount = 128

fun main(args: Array<String>) {
    val firstFileName = "DSC_5824.png.haraff.sift"
    val secondFileName = "DSC_5825.png.haraff.sift"
    val reader = Reader("src/main/resources")
    val picture1 = reader.keyPoints(firstFileName)
    val picture2 = reader.keyPoints(secondFileName)
    println("$firstFileName $secondFileName")

    val indexes1 = picture1.respectivelyClosestIndexesIn(picture2)
    val indexes2 = picture2.respectivelyClosestIndexesIn(picture1)

    indexes1.forEachIndexed { index, indexOfClosest ->
        if (indexes2[indexOfClosest] == index) {
            println("$index $indexOfClosest")
        }
    }
}
