/*
 * Created by Jithin kozhiyodan on 26/1/21 11:40 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 26/1/21 11:40 AM
 *
 */

package com.hav.havlife.view.activity.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hav.havlife.data.communication.repositry.login.UserRepo
import com.hav.havlife.model.request_data.login.OtpRequest
import com.hav.havlife.model.request_data.login.RegisterRequest
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.model.responds_data.login.UserResponds

class UserViewModel: ViewModel() {
    private var userRepo: UserRepo? = null
    init {
        userRepo = UserRepo()
    }
    fun getUserData(request: LoginRequest): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return userRepo!!.getLoginData(request)
    }
    fun setUserData(request: RegisterRequest): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return userRepo!!.setUserData(request)
    }
    fun verifyOTP(request: OtpRequest): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return userRepo!!.verifyOTP(request)
    }
    fun resendOTP(request: OtpRequest): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return userRepo!!.resendOTP(request)
    }
    fun updateProfile(request: ProfileRequest): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return userRepo!!.updateProfile(request)
    }
}