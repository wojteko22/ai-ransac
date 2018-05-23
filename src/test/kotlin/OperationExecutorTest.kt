import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File

internal class OperationExecutorTest {

    private val rootPathname = "src/test/resources/OperationExecutor"
    private val io = OperationExecutor(rootPathname)

    @Test
    fun savePairs() {
        val resultPathname = "auto-deleting.json"
        val resultFile = File("$rootPathname/$resultPathname")
        val expectedJson = File("$rootPathname/expected-pairs.json").readText()

        io.savePairs("1.png.haraff.sift", "2.png.haraff.sift", resultPathname)
        val json = resultFile.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        resultFile.delete()
    }

    @Test
    fun saveConsistentPairs() {
        val resultPathname = "auto-deleting.json"
        val resultFile = File("$rootPathname/$resultPathname")
        val expectedJson = File("$rootPathname/expected-consistent-pairs.json").readText()

        io.saveConsistentPairs("all-pairs.json", resultPathname, 1, 0.9)
        val json = resultFile.readText()

        assertThat(json).isEqualToIgnoringNewLines(expectedJson)

        resultFile.delete()
    }
}
