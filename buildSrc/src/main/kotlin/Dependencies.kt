import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val compose = "1.2.0-beta01"
    const val retrofit = "2.9.0"
    const val hilt = "2.38.1"
    const val accompanist = "0.24.9-beta"
    const val sonarqube = "3.3"
    const val kotlin = "1.6.21"
    const val mockito = "4.5.1"
}

object Dependencies {

    const val gradle = "com.android.tools.build:gradle:7.1.1"

    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
        const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
        const val annotation = "androidx.annotation:annotation:1.3.0"
    }

    object Google {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val coreKtx = "androidx.core:core-ktx:1.0.2"
        const val activityCompose = "androidx.activity:activity-compose:1.3.1"
    }

    object Lifecycle {
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val animation = "androidx.compose.animation:animation-graphics:${Versions.compose}"
        const val paging = "androidx.paging:paging-compose:1.0.0-alpha14"
        const val navigation = "androidx.navigation:navigation-compose:2.4.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    }

    const val timber = "com.jakewharton.timber:timber:4.5.1"
    const val coil = "io.coil-kt:coil-compose:2.0.0"
    const val datastore = "androidx.datastore:datastore-preferences:1.0.0"

    object AndroidXTest {
        // These versions don't seem to be connected: https://developer.android.com/jetpack/androidx/releases/test
        const val core = "androidx.test:core:1.4.0"
        const val junit = "androidx.test.ext:junit-ktx:1.1.3"
        const val runner = "androidx.test:runner:1.4.0"
        const val rules = "androidx.test:rules:1.4.0"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    }

    object Hilt {
        const val plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Accompanist {
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
        const val webView = "com.google.accompanist:accompanist-webview:${Versions.accompanist}"
        const val pager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
        const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    }

    const val jupiter = "org.junit.jupiter:junit-jupiter:5.8.1"
    const val assertJ = "org.assertj:assertj-core:3.22.0"

    object Mockito {
        const val core = "org.mockito:mockito-core:${Versions.mockito}"
        const val junitJupiter = "org.mockito:mockito-junit-jupiter:${Versions.mockito}"
    }
}

object Plugins {
    const val hilt = "dagger.hilt.android.plugin"
    const val sonarqube = "org.sonarqube"
    const val kotlinSerialization = "org.jetbrains.kotlin.plugin.serialization"
    const val kotlinParcelize = "kotlin-parcelize"
}

fun DependencyHandler.pager() {
    implementation(Dependencies.Accompanist.pager)
    implementation(Dependencies.Accompanist.pagerIndicators)
}

fun DependencyHandler.hilt() {
    kapt(Dependencies.Hilt.compiler)
    implementation(Dependencies.Hilt.core)
    implementation(Dependencies.Hilt.navigation)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.gsonConverter)
    implementation(Dependencies.Retrofit.okHttpLogging)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.uiTooling)
    implementation(Dependencies.Compose.uiToolingPreview)
    implementation(Dependencies.Compose.material)
    implementation(Dependencies.Compose.paging)
    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.animation)
    implementation(Dependencies.Compose.constraintLayout)
}

fun DependencyHandler.coreTest() {
    testImplementation(Dependencies.jupiter)
    testImplementation(Dependencies.assertJ)
    testImplementation(Dependencies.Mockito.core)
    testImplementation(Dependencies.Mockito.junitJupiter)
}

fun DependencyHandler.coreInstrumentedTest() {
    androidTestImplementation(Dependencies.assertJ)
    androidTestImplementation(Dependencies.AndroidXTest.core)
    androidTestImplementation(Dependencies.AndroidXTest.junit)
    androidTestImplementation(Dependencies.AndroidXTest.runner)
}

private fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

private fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

private fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}

private fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}
