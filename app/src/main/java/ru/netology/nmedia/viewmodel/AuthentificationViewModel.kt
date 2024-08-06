package ru.netology.nmedia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.netology.nmedia.auth.UserSingIn
import ru.netology.nmedia.model.AuthModel
import javax.inject.Inject

@HiltViewModel
class AuthUserViewModel @Inject constructor(
    private val  userSingIn : UserSingIn
) : ViewModel() {


    private val loginModel = MutableLiveData<AuthModel> ()
    val _loginModel: LiveData<AuthModel>
        get() = loginModel

    fun getAuth(login: String, pass: String) = viewModelScope.launch{
        try {
            userSingIn.getAuth(login, pass)
            loginModel.value = AuthModel(authIsSuccess = true)
        }catch (e: Exception){
            loginModel.value = AuthModel(error = true)
        }
    }






}