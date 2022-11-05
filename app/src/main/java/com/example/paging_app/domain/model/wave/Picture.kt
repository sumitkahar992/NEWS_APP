package com.example.paging_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture (
    val large: String?,
    val medium: String?,
    val thumbnail: String?
): Parcelable

