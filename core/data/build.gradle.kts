plugins {
    id("os.android.library")
    id("os.android.hilt")
}

android {
    namespace = "com.github.despicable.core.data"
}

dependencies {

    // Paging 3.0
    implementation(libs.androidx.paging)

    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.network)

    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
}
