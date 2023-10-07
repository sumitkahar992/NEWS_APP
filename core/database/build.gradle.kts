plugins {
    id("os.android.library")
    id("os.android.hilt")
    id("os.android.room")
}

android {
    namespace = "com.github.despicable.core.database"
}

dependencies {

    implementation(projects.core.model)
    implementation(projects.core.network)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
}
