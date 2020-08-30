import io.github.yeyu.easing.NoEase1D
import org.junit.Assert
import org.junit.Test

class EaseTest {

    @Test
    fun noEaseTest() {
        val from = 0
        val to = 10
        val noEase1D = NoEase1D(from, to)
        val expectedArray = IntArray(5) { it * 2 }
        val actualArray = IntArray(5) { noEase1D.next(it.toFloat() / 5) }
        Assert.assertArrayEquals(expectedArray, actualArray)
    }
}