plugins {
    `android-library-convention`
    id(Plugins.kotlinSerialization)
    id(Plugins.kotlinParcelize)
}

dependencies {
    implementation(Dependencies.Kotlin.kotlinxSerialization)
    implementation(Dependencies.datastore)
    retrofit()

    // NOTE: when adding Room, here are dependencies required:
    //    api "androidx.room:room-runtime:$room_version"
    //    api "androidx.room:room-ktx:$room_version"
    //    kapt "androidx.room:room-compiler:$room_version"

}
android {
    namespace = "hr.asee.android.template.data"
}
