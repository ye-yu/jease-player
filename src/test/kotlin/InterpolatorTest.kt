import io.github.yeyu.easing.function.LinearFunction
import io.github.yeyu.easing.interpolator.DoubleToColor3CInterpolator
import io.github.yeyu.easing.interpolator.DoubleToIntInterpolator
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
        val interpolator = DoubleToIntInterpolator

        Assert.assertEquals(5, interpolator.interpolateNext(from, to, LinearFunction, 0.2))
    }

    @Test
    fun colorInterpolatorTest() {
        val from = Color3C(0, 0, 0)
        val to = Color3C(50, 50, 50)
        val interpolator = DoubleToColor3CInterpolator
        val expected = Color3C(25, 25, 25)
        Assert.assertEquals(expected, interpolator.interpolateNext(from, to, LinearFunction, 0.5))
    }

    @Test
    fun colorInterpolator2Test() {
        val from = Color3C(0, 50, 0)
        val to = Color3C(50, 0, 50)
        val interpolator = DoubleToColor3CInterpolator
        val expected = Color3C(25, 25, 25)
        Assert.assertEquals(expected, interpolator.interpolateNext(from, to, LinearFunction, 0.5))
    }

    @Test
    fun colorInterpolator3Test() {
        val from = Color3C(0, 50, 30)
        val to = Color3C(50, 0, 50)
        val interpolator = DoubleToColor3CInterpolator
        val expected = Color3C(25, 25, 40)
        Assert.assertEquals(expected, interpolator.interpolateNext(from, to, LinearFunction, 0.5))
    }

}