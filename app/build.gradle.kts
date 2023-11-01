
plugins {
    id("os.android.application")
    id("os.android.application.compose")
    id("os.android.hilt")
    id("kotlin-parcelize")
}

android {

    namespace = "com.example.news_app"

    defaultConfig {
        applicationId = "com.example.news_app"
        versionCode = 100
        versionName = "0.0.1"
    }
}

dependencies {

    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)

    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    // Retrofit + Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")

    implementation("androidx.paging:paging-runtime:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")


    implementation("androidx.navigation:navigation-compose:2.7.4")
}
