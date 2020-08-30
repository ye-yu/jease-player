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

    @Test
    fun linearEaseTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val noEase1D = NoEase1D(from, to)

        val at1 = 0f
        val at2 = 0.2f
        val at3 = 0.4f

        val rate1 = noEase1D.next(at2) - noEase1D.next(at1)
        val rate2 = noEase1D.next(at3) - noEase1D.next(at2)

        Assert.assertEquals(rate1, rate2, tolerance)
    }
}