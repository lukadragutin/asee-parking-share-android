plugins {
    `android-library-convention`
}

dependencies {
    implementation(project(":data"))
    implementation(Dependencies.ThreeTen.core)
}
