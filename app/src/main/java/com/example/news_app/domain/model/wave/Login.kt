package com.example.news_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val salt: String?,
    val md5: String?,
    val sha1: String?,
    val sha256: String?
) : Parcelable
