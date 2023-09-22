plugins {
    id("os.android.library")
    id("os.android.library.compose")
}

android {
    namespace = "com.github.despicable.core.designsystem"
}

dependencies {

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)


    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

}
