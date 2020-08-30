import io.github.yeyu.easing.NoEase1D
import io.github.yeyu.easing.QuadraticEaseIn
import io.github.yeyu.easing.QuadraticEaseInOut
import io.github.yeyu.easing.QuadraticEaseOut
import org.junit.Assert
import org.junit.Test

class EaseTest {

    @Test
    fun noEaseTest() {
        val from = 0
        val to = 10
        val noEase1D = NoEase1D(from, to)
        val expectedArray = IntArray(5) { it * 2 }
        val actualArray = IntArray(5) { noEase1D.next(it.toDouble() / 5) }
        Assert.assertArrayEquals(expectedArray, actualArray)
    }

    @Test
    fun linearEaseTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val noEase1D = NoEase1D(from, to)

        val at1 = 0.0
        val at2 = 0.2
        val at3 = 0.4

        val rate1 = noEase1D.next(at2) - noEase1D.next(at1)
        val rate2 = noEase1D.next(at3) - noEase1D.next(at2)

        Assert.assertEquals(rate1, rate2, tolerance)
    }

    @Test
    fun quadraticEaseTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val quadraticEaseIn = QuadraticEaseIn(from, to)

        val at1 = 0.0
        val at2 = 0.2
        val at3 = 0.4

        val rate1 = quadraticEaseIn.next(at2) - quadraticEaseIn.next(at1)
        val rate2 = quadraticEaseIn.next(at3) - quadraticEaseIn.next(at2)
        val multiplier = 0.2 / (3 * 0.2)

        Assert.assertEquals(rate1, rate2 * multiplier, tolerance)
    }

    @Test
    fun inverseQuadraticEaseOutTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val quadraticEaseOut = QuadraticEaseOut(from, to)

        val at1 = 0.6
        val at2 = 0.8
        val at3 = 1.0

        val rate1 = quadraticEaseOut.next(at2) - quadraticEaseOut.next(at1)
        val rate2 = quadraticEaseOut.next(at3) - quadraticEaseOut.next(at2)
        val multiplier = 0.2 / (3 * 0.2)

        Assert.assertEquals(rate1 * multiplier, rate2, tolerance)
    }

    @Test
    fun quadraticEaseInOutTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val quadraticEaseInOut = QuadraticEaseInOut(from, to)

        val at1 = 0.0
        val at2 = 0.2
        val at3 = 0.8
        val at4 = 1.0

        val rate1 = quadraticEaseInOut.next(at2) - quadraticEaseInOut.next(at1)
        val rate2 = quadraticEaseInOut.next(at4) - quadraticEaseInOut.next(at3)

        Assert.assertEquals(rate1, rate2, tolerance)
    }

    @Test
    fun quadraticHalfEaseInOutTest() {
        val from = 0.0
        val to = 10.0
        val tolerance = 0.000015
        val quadraticEaseInOut = QuadraticEaseInOut(from, to)

        Assert.assertEquals(to / 2, quadraticEaseInOut.next(0.5), tolerance)
    }

}