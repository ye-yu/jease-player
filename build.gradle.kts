plugins {
    kotlin("jvm") version Jetbrains.Kotlin.version
    kotlin("plugin.serialization") version Jetbrains.Kotlin.version
    `maven-publish`
}

group = Info.group
version = Info.version

repositories {
    maven(url = "https://kotlin.bintray.com/kotlinx/") { name = "Kotlinx" }
    mavenCentral()
    jcenter()
}

dependencies {
    api(Jetbrains.Kotlin.stdLib)
    api(Jetbrains.Kotlin.reflect)
    api(Jetbrains.Kotlinx.coroutines)
    api(Jetbrains.Kotlinx.serialization)
    testImplementation("junit:junit:4.13")
}

tasks {
    val sourcesJar by creating(Jar::class) {
        archiveClassifier.set("sources")

        from(sourceSets["main"].allSource)

        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    }

    val javadocJar by creating(Jar::class) {
        archiveClassifier.set("javadoc")

        from(project.tasks["javadoc"])

        dependsOn(JavaPlugin.JAVADOC_TASK_NAME)
    }

    compileJava {
        targetCompatibility = Jetbrains.buildTarget
        sourceCompatibility = Jetbrains.buildTarget
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = Jetbrains.buildTarget
            freeCompilerArgs = listOf(
                    "-Xopt-in=kotlin.RequiresOptIn",
                    "-Xopt-in=kotlin.ExperimentalStdlibApi"
            )
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifacts {
                artifact(tasks["sourcesJar"])
                artifact(tasks["javadocJar"])
            }
        }

        repositories {
            mavenLocal()
        }
    }
}
