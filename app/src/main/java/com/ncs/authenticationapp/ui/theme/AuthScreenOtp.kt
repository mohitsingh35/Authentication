package com.ncs.authenticationapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncs.authenticationapp.utils.OtpView
import com.ncs.authenticationapp.utils.loadingscreen
import com.ncs.authenticationapp.utils.showMsg
import com.ncs.guessr.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreenotp(activity: Activity,viewmodel: AuthViewmodel= hiltViewModel()){
    var mobile by remember {
        mutableStateOf("")
    }
    var otp by remember {
        mutableStateOf("")
    }
    var scope= rememberCoroutineScope()
    var isDialog by remember {
        mutableStateOf(false)
    }
    if (isDialog){
        loadingscreen()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp), contentAlignment = Alignment.Center){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Enter Mobile Number")
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(value = mobile , onValueChange = {mobile=it}, label = { Text(text = "+91")}, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal) )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                scope.launch(Dispatchers.Main) {
                    viewmodel.createUserWithPhone(
                        mobile,activity
                    ).collect{
                        when(it){
                            is ResultState.Success->{
                                isDialog=false
                                activity.showMsg(it.data)
                            }
                            is ResultState.Failure->{
                                isDialog=false
                                activity.showMsg(it.msg.toString())
                            }
                            ResultState.Loading->{
                                isDialog=true
                            }
                        }
                    }
                }
            }) {
                Text(text = "Submit")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Enter OTP")
            Spacer(modifier = Modifier.height(20.dp))
            OtpView(otpText = otp){
                otp=it
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { scope.launch(Dispatchers.Main) {
                viewmodel.signwithOtp(
                    otp
                ).collect{
                    when(it){
                        is ResultState.Success->{
                            isDialog=false
                            activity.showMsg(it.data)
                        }
                        is ResultState.Failure->{
                            isDialog=false
                            activity.showMsg(it.msg.toString())
                        }
                        ResultState.Loading->{
                            isDialog=true
                        }
                    }
                }
            } }) {
                Text(text = "Verify")
            }

        }
    }
}