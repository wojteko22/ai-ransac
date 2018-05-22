import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FileHelperTest {

    @Test
    fun keyPoints() {
        val expectedPicture = Picture(listOf(
            KeyPoint(0.0, 0.0, listOf(1, 2)),
            KeyPoint(0.0, 0.0, listOf(3, 4))
        ))
        val reader = FileHelper("src/test/resources")

        val picture = reader.keyPoints("test1.png.haraff.sift")

        assertThat(picture).isEqualTo(expectedPicture)
    }
}
