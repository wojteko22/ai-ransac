import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReaderTest {

    @Test
    fun keyPoints() {
        val expectedKeyPoints = listOf(
            KeyPoint(0.0, 0.0, listOf(1, 2)),
            KeyPoint(0.0, 0.0, listOf(3, 4))
        )
        val reader = Reader()

        val keyPoints = reader.keyPoints("src/test/resources/test1.png.haraff.sift")

        assertThat(keyPoints).isEqualTo(expectedKeyPoints)
    }
}
