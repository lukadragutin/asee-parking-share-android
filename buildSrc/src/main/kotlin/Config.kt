import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "hr.asee.android.template.compose"

    const val versionName = "0.0.1"
    const val versionCode = 1

    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 26

    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}
