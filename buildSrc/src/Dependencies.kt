import org.apache.tools.ant.util.JavaEnvUtils

@Suppress("MemberVisibilityCanBePrivate")
object Jetbrains {

    object Kotlin {
        const val version = "1.4.0"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"

        private const val annotationsVersion = "20.0.0"
        const val annotations = "org.jetbrains:annotations:$annotationsVersion"
    }

    object Kotlinx {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.9"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.9"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0"
    }

    enum class TargetContext(val target: String, val classifier: String? = null) {
        JAVA_11(JavaEnvUtils.JAVA_11),
        JAVA_1_8(JavaEnvUtils.JAVA_1_8, "java8");
    }
}
