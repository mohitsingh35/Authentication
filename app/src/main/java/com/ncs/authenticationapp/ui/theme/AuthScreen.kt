package com.ncs.authenticationapp.ui.theme

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ncs.authenticationapp.OtpLoginActivity
import com.ncs.authenticationapp.firebaseauth.AuthUser
import com.ncs.authenticationapp.utils.loadingscreen
import com.ncs.authenticationapp.utils.showMsg
import com.ncs.guessr.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    viewmodel: AuthViewmodel= hiltViewModel()
){
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    var email1 = remember {
        mutableStateOf("")
    }
    var password1 = remember {
        mutableStateOf("")
    }
    val scope= rememberCoroutineScope()
    val context= LocalContext.current
    var isDialog by remember {
        mutableStateOf(false)
    }
    if(isDialog){
        loadingscreen()
    }
    LazyColumn(Modifier.padding(20.dp)){
        item {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Register")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email.value, onValueChange = {
                    email.value=it
                }, placeholder = { Text(text =" Enter Email")})
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password.value, onValueChange = {
                    password.value=it
                }, placeholder = { Text(text =" Enter password")})
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    scope.launch (Dispatchers.Main){
                        viewmodel.createUser(
                            AuthUser(email.value, password.value)
                        ).collect{
                            when(it){
                                is ResultState.Success ->{
                                    context.showMsg(it.data)
                                    isDialog=false
                                }
                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    isDialog=false
                                }
                                ResultState.Loading->{
                                    isDialog=true
                                }
                            }
                        }
                    }
                }) {
                    Text(text = "Register")
                }

            }
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Login")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email1.value, onValueChange = {
                    email1.value=it
                }, placeholder = { Text(text =" Enter Email")})
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password1.value, onValueChange = {
                    password1.value=it
                }, placeholder = { Text(text =" Enter password")})
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    scope.launch (Dispatchers.Main){
                        viewmodel.loginUser(
                            AuthUser(email1.value, password1.value)
                        ).collect{
                            when(it){
                                is ResultState.Success ->{
                                    context.showMsg(it.data)
                                    isDialog=false
                                }
                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    isDialog=false
                                }
                                ResultState.Loading->{
                                    isDialog=true
                                }
                            }
                        }
                    }
                }) {
                    Text(text = "Login")
                }

            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Box (modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                Button(onClick = { context.startActivity(Intent(context, OtpLoginActivity::class.java)) }) {
                    Text(text = "Sign in with Otp")
                }
            }
        }
    }
}