import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PictureTest {

    private val picture1 = picture(
        listOf(
            listOf(1, 1, 1),
            listOf(5, 5, 5),
            listOf(8, 8, 8)
        )
    )
    private val picture2 = picture(
        listOf(
            listOf(3, 3, 3),
            listOf(2, 2, 2),
            listOf(6, 6, 6)
        )
    )

    private fun picture(features: List<List<Int>>) = Picture(
        features.map { KeyPoint(0.0, 0.0, it) }
    )

    @Test
    fun indexesPairs() {
        val indexesPairs = picture1.indexesPairs(picture2)

        assertThat(indexesPairs).isEqualTo(
            listOf(
                0 to 1,
                1 to 2
            )
        )
    }
}
