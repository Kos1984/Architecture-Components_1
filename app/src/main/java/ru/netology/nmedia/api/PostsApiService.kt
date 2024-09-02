package ru.netology.nmedia.api

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import ru.netology.nmedia.auth.AuthState
import ru.netology.nmedia.dto.Media
import ru.netology.nmedia.dto.Post



interface PostsApiService {

    @GET("posts")
    suspend fun getAll(): Response<List<Post>>
    //метод возьмет последнюю страницу с сервера
    @GET("posts/latest")
    suspend fun getlatest(@Query("count") count : Int): Response<List<Post>>

    @GET("posts/{id}/newer")
    suspend fun getNewer(@Path("id") id: Long): Response<List<Post>>
    //вернет посты которые были до публикации переданного id
    @GET("posts/{id}/before")
    suspend fun getBefore(@Path("id") id: Long, @Query("count") count : Int): Response<List<Post>>
    //вернет посты которые были после публикации переданного id
    @GET("posts/{id}/after")
    suspend fun getAfter(@Path("id") id: Long, @Query("count") count : Int): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getById(@Path("id") id: Long): Response<Post>

    @POST("posts")
    suspend fun save(@Body post: Post): Response<Post>

    @DELETE("posts/{id}")
    suspend fun removeById(@Path("id") id: Long): Response<Unit>

    @POST("posts/{id}/likes")
    suspend fun likeById(@Path("id") id: Long): Response<Post>

    @DELETE("posts/{id}/likes")
    suspend fun dislikeById(@Path("id") id: Long): Response<Post>

    @Multipart
    @POST("media")
    suspend fun upload(@Part media: MultipartBody.Part): Response<Media>

    @FormUrlEncoded
    @POST("users/authentication")
    suspend fun updateUser(@Field("login") login: String, @Field("pass") pass: String): Response<AuthState>
}

