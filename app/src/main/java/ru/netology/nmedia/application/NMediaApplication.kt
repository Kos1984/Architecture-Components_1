package ru.netology.nmedia.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import ru.netology.nmedia.auth.AppAuth
import javax.inject.Inject

@HiltAndroidApp
class NMediaApplication : Application()