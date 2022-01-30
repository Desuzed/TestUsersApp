package com.desuzed.testusersapp.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class SupportDto (
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("text")
    var text: String? = null
)