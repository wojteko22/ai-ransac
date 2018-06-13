import kotlin.system.measureTimeMillis

fun measure(function: () -> Unit) {
    val millis = measureTimeMillis { function() }
    println("Millis: $millis")
}
