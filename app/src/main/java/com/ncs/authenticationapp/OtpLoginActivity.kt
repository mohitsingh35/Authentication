package com.ncs.authenticationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ncs.authenticationapp.ui.theme.AuthScreenotp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpLoginActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthScreenotp(this)
        }
    }
}