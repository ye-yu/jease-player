import io.github.yeyu.easing.NoEase1D
import io.github.yeyu.easing.player.EasePlayer
import io.github.yeyu.easing.player.PersistentEasePlayer
import io.github.yeyu.easing.player.RollingEasePlayer
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

        var n = 1
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
        val transitionTo = 5.0
        val noEasePlayer = EasePlayer(from, to, numberOfFrames) { f: Double, t: Double -> NoEase1D(f, t) }
        noEasePlayer.transitionTo = 6.0

        var n = 1
        while(noEasePlayer.hasNext()) {
            noEasePlayer.next()
            n++
        }

        noEasePlayer.transitionTo = transitionTo

        val next = noEasePlayer.next()
        Assert.assertTrue("$next is not less than 6.0", next < 6.0)
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

}