@Suppress("DSL_SCOPE_VIOLATION")
plugins {

    id("kotlin")
    kotlin("kapt")

}

dependencies {


    implementation(libs.moshi)
    implementation(libs.moshi.converter)
    kapt(libs.moshi.kapt)

}