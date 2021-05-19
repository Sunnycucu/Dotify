package edu.uw.sunny121.dotify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Userprofile (
    val username: String,
    val firstName : String,
    val lastName : String,
    val hasNose : Boolean,
    val platform : Double,
    val profilePicURL : String
) : Parcelable

