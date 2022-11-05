package com.example.paging_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ID (
    val name: String?,
    val value: String?,
): Parcelable
