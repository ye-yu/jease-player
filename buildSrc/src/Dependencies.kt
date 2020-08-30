import org.apache.tools.ant.util.JavaEnvUtils.JAVA_11

object Jetbrains {
    private const val annotationsVersion = "17.0.0"
    const val buildTarget = JAVA_11
    const val annotations = "org.jetbrains:annotations:$annotationsVersion"

    object Kotlin {
        const val version = "1.4.0"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
    }

    object Kotlinx {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.7"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0"
    }
}
