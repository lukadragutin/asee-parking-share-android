@file:Suppress("UnstableApiUsage")

import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = true
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
        freeCompilerArgs = freeCompilerArgs.plus("-opt-in=kotlin.RequiresOptIn")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    packagingOptions {
        resources {
            // "It's perfectly safe to exclude all meta-info files which are there just for documentation and information purposes"
            // Reference: https://stackoverflow.com/a/68136374/7111456, https://stackoverflow.com/a/47509465/7111456
            // Basically META-INF will include apk metadata such as licence, dependencies, etc. which are not needed for a normal functioning application.
            excludes.add("META-INF/*")
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.core)
    implementation(Dependencies.Kotlin.coroutines)
    implementation(Dependencies.Kotlin.annotation)
    implementation(Dependencies.Google.appCompat)
    implementation(Dependencies.Google.coreKtx)
    implementation(Dependencies.Lifecycle.runtimeKtx)
    implementation(Dependencies.Lifecycle.viewmodelCompose)
    implementation(Dependencies.Google.activityCompose)
    compose()

    coreTest()
    coreInstrumentedTest()
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}
