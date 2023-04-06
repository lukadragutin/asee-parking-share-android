buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.gradle)
        classpath(Dependencies.Hilt.plugin)
        classpath(Dependencies.Kotlin.serialization)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks {
}
