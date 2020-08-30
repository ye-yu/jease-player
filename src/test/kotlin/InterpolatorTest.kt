import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.interpolator.IntInterpolator1D
import org.junit.Assert
import org.junit.Test

class InterpolatorTest {

    @Test
    fun linearFunctionTest() {
        val test = 5.0
        val tolerance = 0.000015
        Assert.assertEquals(test, LinearFunction.f(test), tolerance)
    }

    @Test
    fun linearIntInterpolatorTest() {
        val from = 2
        val to = 18
        val interpolator = IntInterpolator1D(from, to)

        Assert.assertEquals(5, interpolator.next(0.2f))
    }

}