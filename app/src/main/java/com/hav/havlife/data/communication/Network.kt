/*
 * Created by Jithin kozhiyodan on 25/1/21 10:31 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 25/1/21 10:31 AM
 *
 */

package com.hav.havlife.data.communication

import com.hav.havlife.model.request_data.login.OtpRequest
import com.hav.havlife.model.request_data.login.RegisterRequest
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import retrofit2.Call
import retrofit2.http.*

interface Network {

    //Login
    @POST("login")
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun login(@Body request: LoginRequest): Call<UserResponds>
    //Register
    @POST("signup")
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun register(@Body request: RegisterRequest): Call<UserResponds>
    //Confirm
    @POST("confirmuser")
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun confirm(@Body request: OtpRequest): Call<UserResponds>
    //Resend
    @POST("mfacode")
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun resend(@Body request: OtpRequest): Call<UserResponds>
    //Profile
    @POST("api/users")
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun profile(@Body request: ProfileRequest): Call<UserResponds>
    //Profile
    @GET
    @Headers("Content-Type: application/json","Accept-Language:en")
    abstract fun dashboard(@Url url:String): Call<UserResponds>


}