import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.interpolator.GenericInterpolator1D
import io.github.yeyu.easing.interpolator.IntInterpolator1D
import io.github.yeyu.easing.type.Color3C
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

        Assert.assertEquals(5, interpolator.next(0.2))
    }

    @Test
    fun colorInterpolatorTest() {
        val from = Color3C(0, 0, 0)
        val to = Color3C(50, 50, 50)
        val interpolator = GenericInterpolator1D(from, to)
        val expected = Color3C(25, 25, 25)
        Assert.assertEquals(expected, interpolator.next(0.5))
    }

}