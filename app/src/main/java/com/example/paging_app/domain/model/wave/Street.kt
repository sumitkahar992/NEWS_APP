package com.example.paging_app.domain.model.wave

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street (
    val number: Long?,
    val name: String?
) : Parcelable