package com.ncs.authenticationapp.ui.theme

import androidx.lifecycle.ViewModel
import com.ncs.authenticationapp.firebaseauth.AuthUser
import com.ncs.authenticationapp.firebaseauth.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val repo:AuthRepository
) :ViewModel(){
    fun createUser(authUser:AuthUser) = repo.createUser((authUser))
    fun loginUser(authUser: AuthUser)=repo.loginUser(authUser)
}