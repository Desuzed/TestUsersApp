package com.desuzed.testusersapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
): Parcelable