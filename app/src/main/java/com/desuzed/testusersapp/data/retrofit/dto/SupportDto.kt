package com.desuzed.testusersapp.data.retrofit.dto

import com.desuzed.testusersapp.data.EntityMapper
import com.desuzed.testusersapp.data.model.Support
import com.google.gson.annotations.SerializedName

class SupportDto(
    @SerializedName("url")
    var url: String = "",
    @SerializedName("text")
    var text: String = ""
)

class SupportMapper : EntityMapper<SupportDto, Support> {
    override fun mapFromEntity(entity: SupportDto): Support {
        return Support(entity.url, entity.text)
    }
}