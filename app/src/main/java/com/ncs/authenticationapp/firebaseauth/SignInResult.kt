package com.ncs.authenticationapp.firebaseauth

data class SignInResult(
    val data:UserData?,
    val errorMessage:String?
)
data class UserData(
    val userID:String,
    val username:String?,
    val profilePictureUrl:String?
)
