package com.example.paging_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WaveResponse(
    val results: List<Results>?,
    val info: Info?,
) : Parcelable
