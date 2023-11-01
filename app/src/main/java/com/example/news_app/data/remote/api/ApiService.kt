package com.example.news_app.data.remote.api

import com.example.news_app.domain.model.paging.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("user")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): UserResponse

    companion object {

        private const val BASE_URL = "https://dummyapi.io/data/v1/"

        operator fun invoke(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        private fun getRetrofitClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder().also {
                            it.addHeader("Accept", "application/json")
                            it.addHeader("app-id", "6363f81bcf8f4e1521623233")
                        }.build()
                    )
                }.also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build()
        }
    }
}
