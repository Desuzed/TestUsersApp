package com.desuzed.testusersapp.data.retrofit.dto

import com.google.gson.annotations.SerializedName

class Response(
    @SerializedName("page") var page: Int? = null,
    @SerializedName("per_page") var perPage: Int? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("data") var users: ArrayList<UserRetrofitDto> = arrayListOf(),
    @SerializedName("support") var support: SupportDto? = SupportDto()
)