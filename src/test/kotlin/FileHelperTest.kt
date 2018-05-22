import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class FileHelperTest {

    private val rootPathname = "src/test/resources"
    private val reader = FileHelper(rootPathname)

    @Test
    fun keyPoints() {
        val expectedPicture = Picture(
            listOf(
                KeyPoint(0.0, 0.0, listOf(1, 2)),
                KeyPoint(0.0, 0.0, listOf(3, 4))
            )
        )

        val picture = reader.keyPoints("test1.png.haraff.sift")

        assertThat(picture).isEqualTo(expectedPicture)
    }

    @Test
    fun save() {
        val pathname = "auto-deleting.json"
        val content = listOf("a", "b", "c")
        val file = File("$rootPathname/$pathname")
        val expectedJson = File("$rootPathname/expected.json").readText()

        reader.save(pathname, content)
        val json = file.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        file.delete()
    }
}
