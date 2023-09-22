plugins {
    id("os.android.library")
    id("os.android.library.compose")
}

android {
    namespace = "com.github.despicable.core.ui"
}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.model)


    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.extended)


    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
}
