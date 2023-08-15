package com.ncs.authenticationapp.firebaseauth

data class GoogleSignInState(
    val isSignInSuccessful:Boolean=false,
    val signInError:String?=null
)
