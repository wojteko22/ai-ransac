fun main(args: Array<String>) {
    val firstFileName = "DSC_5824.png.haraff.sift"
    val secondFileName = "DSC_5825.png.haraff.sift"
    val reader = Reader("src/main/resources")
    val picture1 = reader.keyPoints(firstFileName)
    val picture2 = reader.keyPoints(secondFileName)
    println("$firstFileName $secondFileName")

    picture1.indexesPairs(picture2).forEach {
        println("${it.first} ${it.second}")
    }
}
