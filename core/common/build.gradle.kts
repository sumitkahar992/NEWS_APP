plugins {
    id("os.android.library")
    id("os.android.hilt")
}

android {
    namespace = "com.github.despicable.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}
