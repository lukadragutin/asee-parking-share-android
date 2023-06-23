import org.jetbrains.kotlin.konan.properties.Properties
import java.util.Date
import java.text.SimpleDateFormat
import java.io.FileInputStream

plugins {
    `android-compose-app-convention`
    id(Plugins.hilt)
    id(Plugins.sonarqube) version Versions.sonarqube
}

apply(plugin = "dagger.hilt.android.plugin")

android {

    defaultConfig {
        applicationId = Config.applicationId
        versionCode = Config.versionCode
        versionName = Config.versionName
        minSdk = Config.minSdk
    }

    /*val releaseSigningConfigName = "release"
    val keystorePropertiesFile = file("../keystore/keystore.properties")
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))

    signingConfigs {
        create(releaseSigningConfigName) {
            storeFile = file("${rootDir.canonicalPath}/${keystoreProperties.getProperty("releaseKeystore")}")
            keyAlias = keystoreProperties.getProperty("releaseKeyAlias")
            keyPassword = keystoreProperties.getProperty("releaseKeyPassword")
            storePassword = keystoreProperties.getProperty("releaseStorePassword")
        }
    }*/

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            //signingConfig = signingConfigs.getByName(releaseSigningConfigName)
        }
    }

    val environmentFlavor = "environment"
    flavorDimensions.add(environmentFlavor)

    productFlavors {
        create("dev") {
            dimension = environmentFlavor
            applicationIdSuffix = ".dev"
        }
    }

    applicationVariants.all {
        val variant = this
        val timestamp = SimpleDateFormat("yyyy-MM-dd_HH-mm").format(Date())
        variant.outputs
            .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
            .forEach { output ->
                val outputFileName = "JetpackComposeSampleApp_$timestamp-v${variant.versionName}-${variant.versionCode}-${variant.name}.apk"
                output.outputFileName = outputFileName
            }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    debugImplementation(Dependencies.timber)
    implementation(Dependencies.Accompanist.swipeRefresh)
    implementation(Dependencies.Accompanist.webView)
    implementation(Dependencies.coil)
    implementation(Dependencies.datastore)

    pager()
    hilt()
    retrofit()
}

kapt {
    correctErrorTypes = true
}
