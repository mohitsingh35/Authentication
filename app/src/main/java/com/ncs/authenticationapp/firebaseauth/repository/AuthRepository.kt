package com.ncs.authenticationapp.firebaseauth.repository

import android.app.Activity
import com.ncs.authenticationapp.firebaseauth.AuthUser
import com.ncs.guessr.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun createUser(
        auth:AuthUser
    ): Flow<ResultState<String>>
    fun loginUser(
        auth: AuthUser
    ): Flow<ResultState<String>>

    fun createUserWithPhone(
        phone:String,
        activity: Activity
    ):Flow<ResultState<String>>

    fun signInwithotp(
        otp:String
    ):Flow<ResultState<String>>
}
