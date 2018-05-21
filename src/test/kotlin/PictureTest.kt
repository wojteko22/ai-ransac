import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PictureTest {

    @Test
    fun respectivelyClosestIndexesIn() {
        val picture1 = picture(
            listOf(
                listOf(1, 1, 1),
                listOf(5, 5, 5)
            )
        )
        val picture2 = picture(
            listOf(
                listOf(3, 3, 3),
                listOf(2, 2, 2),
                listOf(6, 6, 6)
            )
        )

        val indexes = picture1.respectivelyClosestIndexesIn(picture2)

        assertThat(indexes).isEqualTo(
            listOf(1, 2)
        )
    }

    private fun picture(features: List<List<Int>>) = Picture(
        features.map { KeyPoint(0.0, 0.0, it) }
    )
}
