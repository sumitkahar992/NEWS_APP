
plugins {
    id("os.android.library")
    kotlin("kapt")
}

android {

    namespace = "com.github.despicable.core.domain"
}

dependencies {

    // Paging 3.0
    implementation(libs.androidx.paging)

    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}
