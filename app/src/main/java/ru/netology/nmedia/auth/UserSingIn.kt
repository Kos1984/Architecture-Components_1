package ru.netology.nmedia.auth

import ru.netology.nmedia.api.PostsApiService
import ru.netology.nmedia.error.ApiError
import ru.netology.nmedia.error.NetworkError
import ru.netology.nmedia.error.UnknownError
import java.io.IOException
import javax.inject.Inject

class UserSingIn @Inject constructor(
    private val postsApiService: PostsApiService,
    private val appAuth: AppAuth,
) {
    suspend fun getAuth(login: String, pass: String) {
        try {
            val response = postsApiService.updateUser(login, pass)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            appAuth.setAuth(body.id, body.token.toString())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}