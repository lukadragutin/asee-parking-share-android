plugins {
    `android-library-convention`
}

dependencies {
    implementation(project(":data"))
}
android {
    namespace = "hr.asee.android.template.domain"
}
