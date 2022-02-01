package com.desuzed.testusersapp.data.retrofit

import com.desuzed.everyweather.data.network.retrofit.NetworkResponse
import com.desuzed.everyweather.data.network.retrofit.NetworkResponseAdapterFactory
import com.desuzed.testusersapp.data.retrofit.dto.ErrorRetrofitDto
import com.desuzed.testusersapp.data.retrofit.dto.ResponseDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
interface UserService {
    @GET("users?")
    suspend fun fetchResponse(
        @Query("page") query: String
    ): NetworkResponse<ResponseDto, ErrorRetrofitDto>

    companion object{
        private const val baseUrl = "https://reqres.in/api/"
        private var weatherApiService : UserService? = null
        fun getInstance() : UserService {
            if (weatherApiService == null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(NetworkResponseAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                weatherApiService = retrofit.create(UserService::class.java)
            }
            return weatherApiService!!
        }
    }
}