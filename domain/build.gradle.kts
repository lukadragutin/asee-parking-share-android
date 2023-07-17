plugins {
    `android-library-convention`
}

dependencies {
    implementation(project(":data"))
    implementation(Dependencies.ThreeTen.core)
}
android {
    namespace = "hr.asee.android.template.domain"
}
