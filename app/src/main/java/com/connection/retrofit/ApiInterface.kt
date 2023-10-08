package com.connection.retrofit

import com.connection.retrofit.models.User
import retrofit2.Response
import retrofit2.http.GET


interface ApiInterface {
    @GET("/posts/1")
    suspend fun getUser():Response<User>
}