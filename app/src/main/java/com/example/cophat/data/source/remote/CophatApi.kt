package com.example.cophat.data.source.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface CophatApi {

    @POST("save/firebase")
    suspend fun login(@Body payload: String): String
}