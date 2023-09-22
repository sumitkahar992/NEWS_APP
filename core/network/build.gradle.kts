plugins {
    id("os.android.library")
    id("os.android.hilt")
}

android {
    namespace = "com.github.despicable.core.network"
}

dependencies {



    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    kapt(libs.moshi.kapt)


    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
}
