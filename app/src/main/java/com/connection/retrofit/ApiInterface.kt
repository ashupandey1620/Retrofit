package com.connection.retrofit

import com.connection.retrofit.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiInterface {
    @GET("/posts/1")
    suspend fun getUser():Response<User>

    @POST("/posts")
    suspend fun createPost(
        @Body user: User
    ):Response<User>

    @FormUrlEncoded
    @POST("/posts")
    suspend fun createUrlPost(
        @Field("userId") userId : Int ,
        @Field("title") title : String ,
        @Field("body") body :String ,
    ):Response<User>

    @PUT("posts/{id}")
    suspend fun putPost(
        @Path("id") id : Int ,
        @Body user: User
    ):Response<User>


    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") id : Int,
        @Body user: User
    ):Response<User>

    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id : Int
    ) : Response<User>
}