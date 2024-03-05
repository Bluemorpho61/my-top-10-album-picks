package com.alkin.coy

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val name: String,
    val artist:String,
    val release:String,
    val description: String,
    val photo: Int
) : Parcelable
