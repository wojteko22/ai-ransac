class PairsIOExecutor(rootPathname: String) {

    private val io = FileHelper(rootPathname)

    fun saveConsistentPairs(pairsPathname: String, destPathname: String, neighborhoodSize: Int, threshold: Double) {
        val pairs = io.pointsPairs(pairsPathname)!!
        val consistentPairs = PairsProcessor.consistentPairs(pairs, neighborhoodSize, threshold)
        io.save(destPathname, consistentPairs)
    }

    fun savePairs(fileName1: String, fileName2: String, resultPathname: String) {
        val picture1 = io.keyPoints(fileName1)
        val picture2 = io.keyPoints(fileName2)
        val pairs = picture1.keyPointsPairs(picture2)
        io.save(resultPathname, pairs)
    }
}
