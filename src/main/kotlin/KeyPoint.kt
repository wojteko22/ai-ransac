data class KeyPoint(val x: Double, val y: Double, @Transient val features: List<Int>) {

    @Transient
    val size = features.size
}
