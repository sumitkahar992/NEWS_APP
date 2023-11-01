package com.example.news_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val seed: String?,
    val results: Long?,
    val page: Long?,
    val version: String?
) : Parcelable
