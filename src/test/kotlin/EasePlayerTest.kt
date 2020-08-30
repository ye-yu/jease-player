import io.github.yeyu.easing.NoEase1D
import io.github.yeyu.easing.player.EasePlayer
import io.github.yeyu.easing.player.PersistentEasePlayer
import io.github.yeyu.easing.player.ReversingEasePlayer
import io.github.yeyu.easing.player.RollingEasePlayer
import io.github.yeyu.easing.type.Color3C
import io.github.yeyu.easing.type.Color4C
import org.junit.Assert
import org.junit.Test

class EasePlayerTest {

    @Test
    fun linearDoubleEasePlayerTest() {
        val from = 0.0
        val to = 10.0
        val numberOfFrames = 20
        val transitionTo = 5.0
        val tolerance = 0.000015
        val noEasePlayer = EasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEasePlayer.transitionTo = transitionTo

        var n = 0
        var last = 0.0
        while(noEasePlayer.hasNext()) {
            last = noEasePlayer.next()
            println("Next of $last")
            n++
        }

        Assert.assertEquals(numberOfFrames, n)
        Assert.assertEquals(transitionTo, last, tolerance)
    }

    @Test
    fun linearDoubleTwoWayEasePlayerTest() {
        val from = 0.0
        val to = 10.0
        val numberOfFrames = 20
        val transitionMiddle = 6.0
        val transitionTo = 5.0
        val tolerance = 0.000015
        val noEasePlayer = EasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEasePlayer.transitionTo = transitionMiddle

        var n = 1
        while(noEasePlayer.hasNext()) {
            noEasePlayer.next()
            n++
        }

        noEasePlayer.transitionTo = transitionTo

        Assert.assertEquals(transitionMiddle, noEasePlayer.next(), tolerance)
        val next = noEasePlayer.next()
        Assert.assertTrue("$next is not less than $transitionMiddle", next < transitionMiddle)
    }

    @Test
    fun persistentEasePlayerTest() {
        val from = 0.0
        val to = 10.0
        val numberOfFrames = 20
        val transitionTo = 5.0
        val tolerance = 0.000015
        val noEasePersistentPlayer = PersistentEasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEasePersistentPlayer.transitionTo = transitionTo

        for(i in 0 until numberOfFrames) noEasePersistentPlayer.next()

        Assert.assertEquals(noEasePersistentPlayer.next(), noEasePersistentPlayer.next(), tolerance)
    }

    @Test
    fun rollingEasePlayerTest() {
        val from = 0.0
        val to = 10.0
        val numberOfFrames = 20
        val transitionTo = 5.0
        val tolerance = 0.000015
        val noEaseRollingPlayer = RollingEasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEaseRollingPlayer.transitionTo = transitionTo

        for(i in 0 until numberOfFrames) noEaseRollingPlayer.next()

        Assert.assertEquals(from, noEaseRollingPlayer.next(), tolerance)
        val next = noEaseRollingPlayer.next()
        Assert.assertTrue("$next is not more than $from", next > from)
    }

    @Test
    fun reversingEasePlayerTest() {
        val from = 0.0
        val to = 10.0
        val numberOfFrames = 20
        val transitionTo = 5.0
        val tolerance = 0.000015
        val noEaseReversingPlayer = ReversingEasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEaseReversingPlayer.transitionTo = transitionTo

        var last = 0.0
        for(i in 0 until numberOfFrames) {
            last = noEaseReversingPlayer.next()
        }
        Assert.assertEquals(transitionTo, last, tolerance)

        val next = noEaseReversingPlayer.next()
        Assert.assertTrue("$next is not less than $transitionTo", next < transitionTo)
    }

    @Test
    fun colorEasePlayerTest() {
        val from = Color4C(0, 0, 0, 0)
        val to = Color4C(80, 80, 50, 60)
        val numberOfFrames = 10
        val colorEasePlayer = PersistentEasePlayer(from, to, numberOfFrames, ::NoEase1D)
        colorEasePlayer.transitionTo = to

        var last = Color4C(0, 0, 0, 0)
        for (i in 0 until numberOfFrames) {
            last = colorEasePlayer.next()
            println("Color to $last")
        }

        Assert.assertEquals(to, last)
    }

    @Test
    fun colorEasePlayer2Test() {
        val from = Color4C(0, 80, 0, 0)
        val to = Color4C(80, 0, 50, 60)
        val numberOfFrames = 10
        val colorEasePlayer = PersistentEasePlayer(from, to, numberOfFrames, ::NoEase1D)
        colorEasePlayer.transitionTo = to

        var last = Color4C(0, 0, 0, 0)
        for (i in 0 until numberOfFrames) {
            last = colorEasePlayer.next()
            println("Color to $last")
        }

        Assert.assertEquals(to, last)
    }

    @Test
    fun colorEasePlayer3Test() {
        val from = Color3C(0, 80, 0)
        val to = Color3C(80, 0, 0)
        val numberOfFrames = 10
        val colorEasePlayer = PersistentEasePlayer(from, to, numberOfFrames, ::NoEase1D)
        colorEasePlayer.transitionTo = to

        var last = Color3C(0, 0, 0)
        for (i in 0 until numberOfFrames) {
            last = colorEasePlayer.next()
            println("Color to $last")
        }

        Assert.assertEquals(to, last)
    }

}