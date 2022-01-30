package com.desuzed.testusersapp.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class UserRetrofitDto (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("email")
    var email: String = "",
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("avatar")
    var avatar: String?= ""
)