package com.desuzed.testusersapp.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class ErrorRetrofitDto {
    @SerializedName("status")
    var status: String = ""

    @SerializedName("code")
    var code: String = ""

    @SerializedName("message")
    var message: String = ""
}