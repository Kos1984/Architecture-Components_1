package ru.netology.nmedia.model

import ru.netology.nmedia.auth.AuthState

data class AuthModel (
    var error: Boolean = false,
    val authIsSuccess: Boolean = false,
)