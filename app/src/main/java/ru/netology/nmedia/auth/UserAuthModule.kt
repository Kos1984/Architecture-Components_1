package ru.netology.nmedia.auth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.netology.nmedia.api.PostsApiService

@InstallIn(SingletonComponent::class)
@Module
class UserAuthModule {
    @Provides
    fun provideUserSingIn(
        postsApiService: PostsApiService,
        appAuth: AppAuth,
    ): UserSingIn = UserSingIn(postsApiService, appAuth)
}