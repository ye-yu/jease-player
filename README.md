[![Maven Central](https://img.shields.io/maven-central/v/io.github.ye-yu/jeasing.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.ye-yu%22%20AND%20a:%22jeasing%22)
[![build tests](https://github.com/ye-yu/jease-player/workflows/Tests/badge.svg)](https://github.com/ye-yu/jease-player/actions)

# Jeasing - Java Easing Player Library

Jeasing is an easing library with iterator called
an ease player.

## Importing
Add this to your dependency

For gradle
```build.gradle
implementation "io.github.yeyu:jeasing:0.0.1-alpha.1"

// or if you are building for java 1.8
implementation "io.github.yeyu:jeasing:0.0.1-alpha.1:java8"
```

For other:
  - group id = io.github.yeyu
  - artifact = jeasing
  - version = 0.0.1-alpha.1
  - classifier = java8 (specify if you compile to java 8)
  
## Quick tutorial

An ease function is a function for easing animation 
with the domain of `[0, 1]` depicting the moment of
the frame animation (where 0 is the start of the animation
and 1 is the end of the animation). 

An ease-in function means
the animation effect is at the start of animation while an
ease-out function means the animation effect is at the end
of the animation. Finally, an ease-inout function is a function
that applies ease-in at `[0, 0.5)` and then applies ease-out at
`[0.5, 1]`.

This library provides a basic implementation of easing
featuring:
  - `Function` - a function of many-to-one ideally with the domain of `[0, 1]` and the range of `[0, 1]` where `f(0) = 0` and `f(1) = 1`
  - `Interpolator` - an interpolator class using function
  - `Ease/EaseIn/EaseOut/EaseInOut` - an implementation of interpolator with easing features

### <a name="ease"></a>Using Ease

This is how you can use the easing function:

```kotlin
import io.github.yeyu.easing.EaseInImpl
import io.github.yeyu.easing.function.QuadraticFunction

val from = 0.5
val to = 1.4
val easeIn = EaseInImpl(from, to, QuadraticFunction)

val frames = 20

for(i in 0 until frames) {
    val at = i.toDouble() / (frames - 1) // do - 1 for creating domains of [0, 1]
    val next = easeIn.next(at)
    println("Animation: $next%")
}
```

Easing bound is versatile as the `from` value does not have to be lower than
the `to` value.

### Defining custom function

You can define your own function in two ways:

1. Implementing `Function` interface

    ```kotlin
    import io.github.yeyu.function.Function
    object Cubic: Function {
        override fun f(x: Double): Double = x * x * x
    }  
    ```

2. Using function maker:

    This could be useful for users that don't want to create extra files for a class implementing function
    
    In Kotlin:
    
    ```kotlin
    import io.github.yeyu.function.FunctionMaker
    val cubic = FunctionMaker.create { x: Double -> x * x * x }
    ```

    In Java:

    ```java
    import io.github.yeyu.function.FunctionMaker;
    Function cubic = FunctionMaker.create(x -> x * x * x)
    ```

## Using Ease Player

Ease Player is a utility class to iterate through the values of the easing
from the defined number of frames. This ease player will calculate the next
frame at each `next()` call. Therefore, [this code](#ease) will be transformed into:

```kotlin
import io.github.yeyu.easing.EaseInImpl
import io.github.yeyu.easing.easeplayer.FramefulEasePlayer
import io.github.yeyu.easing.function.QuadraticFunction

val from = 0.5
val to = 1.4

val frames = 20
val easePlayer = FramefulEasePlayer(from, to, 20) { f: Double, t: To ->
    EaseInImpl(f, t, QuadraticFunction)
}

for(i in 0 until frames) {
    val next = easePlayer.next() // no longer providing at
    println("Animation: $next%")
}

easePlayer.next() // throws NoSuchElementException
```

### Use Case

Ease Player is useful when calling a render method in a loop by 
simplifying extra the calculation. 

```kotlin
import io.github.yeyu.easing.EaseInImpl
import io.github.yeyu.easing.easeplayer.FramefulEasePlayer
import io.github.yeyu.easing.function.QuadraticFunction

val from = 0.5
val to = 1.4

val frames = 20
val easePlayer = FramefulEasePlayer(from, to, 20) { f: Double, t: To ->
    EaseInImpl(f, t, QuadraticFunction)
}

// to be called in every render frame
fun render() { 
    val height = easePlayer.next() // may throw exception after calling 20 times
    DrawerInstance.drawNewHeight(height = height) // some imaginary object
}
```

### Different Player Flavours

Any instance of `FramefulEasePlayer` will throw an exception if called again after
reaching the maximum number of frames. Here are other players that might be
useful if you are expecting to call `next()` infinitely many times.

The following classes extend `FramefulEasePlayer`:

  - `PersistentFramefulEasePlayer` - replays the last frame after reaching the end of the frame
  - `RollingFramefulEasePlayer` - replays the animation from the start after reaching the end of the frame
  - `ReversingFramefulEasePlayer` - reverse the animation after at the end and at the start of the frame
  
All `FramefulEasePlayer` types are not frame-per-second(FPS) friendly as
the number of frame does not scale to the FPS. That is, the lower
the FPS, the slower the time taken for the animation to complete. 
To overcome this, you may use these different variants of ease player that
calculates the next frame based on the system time deltas.

  - `PersistentTimeEasePlayer` - replays the last frame after reaching the end of the frame
  - `RollingTimeEasePlayer` - replays the animation from the start after reaching the end of the frame
  - `ReversingTimeEasePlayer` - reverse the animation after at the end and at the start of the frame

### Using `transitionTo`

The property `transitionTo` is for setting different transition point inside of 
the bound. For example, if the bound is set to `[2, 6]` and the `transitionTo`
is set to `5`, the ease player will calculate the same number of frames
but only until `5`. If the `transitionTo` property is set before reaching
the end of the frame, the ease player will start from the intermediate value
and calculate another frames until reaching the new `transitionTo`.

For example,

```kotlin
import io.github.yeyu.easing.EaseInImpl
import io.github.yeyu.easing.easeplayer.FramefulEasePlayer
import io.github.yeyu.easing.function.QuadraticFunction

val from = 0.5
val to = 1.4

val frames = 20
val easePlayer = FramefulEasePlayer(from, to, 20) { f: Double, t: To ->
    EaseInImpl(f, t, QuadraticFunction)
}

// for java, use setTransitionTo()
easePlayer.transitionTo = 1.0


for(i in 0 until frames) {
    val next = easePlayer.next() // no longer providing at
    println("Animation: $next%")
}
// the last print will be "Animation: 1.0"

easePlayer.transitionTo = 0.8
easePlayer.next()
easePlayer.next()
easePlayer.next() // say the value here is 0.95

easePlayer.transitionTo = 1.4 
// can now call another 20 frames
// and interpolate from 0.95 to 1.4
```
