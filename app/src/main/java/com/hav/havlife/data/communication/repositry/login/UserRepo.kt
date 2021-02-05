/*
 * Created by Jithin kozhiyodan on 25/1/21 11:54 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 25/1/21 11:54 AM
 *
 */

package com.hav.havlife.data.communication.repositry.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.hav.havlife.data.communication.Api
import com.hav.havlife.data.communication.Network
import com.hav.havlife.model.request_data.login.OtpRequest
import com.hav.havlife.model.request_data.login.RegisterRequest
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo {
    fun getLoginData(request: LoginRequest):MutableLiveData<UserResponds>{
        val loginLiveData = MutableLiveData<UserResponds>()

        Api.getClient()!!.create(Network::class.java).login(request)
            .enqueue(object : Callback<UserResponds> {
                override fun onFailure(call: Call<UserResponds>, t: Throwable) {
                    var responds= UserResponds()
                    responds.status = false
                    responds.timeout = true
                    loginLiveData.value = responds
                }

                override fun onResponse(
                    call: Call<UserResponds>,
                    response: Response<UserResponds>
                ) {
                    //http://13.232.205.158:3001/login
                    var responds:UserResponds
                    if(response.code()==200){
                        responds = response.body()!!
                        responds.status=true
                        loginLiveData.value = responds
                    }else{
                        responds= UserResponds()

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Log.i("errorBody", jObjError.toString())
                            //responds.errorCode = jObjError.getInt("error_code")
                            responds.message = jObjError.getString("message")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //responds = response.errorBody()
                        responds.status=false
                        loginLiveData.value = responds
                    }

                }
            })

        return loginLiveData
    }
    fun setUserData(request: RegisterRequest):MutableLiveData<UserResponds>{
        val loginLiveData = MutableLiveData<UserResponds>()

        Api.getClient()!!.create(Network::class.java).register(request)
            .enqueue(object : Callback<UserResponds> {
                override fun onFailure(call: Call<UserResponds>, t: Throwable) {
                    var responds= UserResponds()
                    responds.status = false
                    responds.timeout = true
                    loginLiveData.value = responds
                }

                override fun onResponse(
                    call: Call<UserResponds>,
                    response: Response<UserResponds>
                ) {
                    //http://13.232.205.158:3001/login
                    var responds:UserResponds
                    if(response.code()==200){
                        responds = response.body()!!
                        responds.status=true
                        loginLiveData.value = responds
                    }else{
                        responds= UserResponds()

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Log.i("errorBody", jObjError.toString())
                            //responds.errorCode = jObjError.getInt("error_code")
                            responds.message = jObjError.getString("message")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //responds = response.errorBody()
                        responds.status=false
                        loginLiveData.value = responds
                    }

                }
            })

        return loginLiveData
    }
    fun verifyOTP(request: OtpRequest):MutableLiveData<UserResponds>{
        val loginLiveData = MutableLiveData<UserResponds>()

        Api.getClient()!!.create(Network::class.java).confirm(request)
            .enqueue(object : Callback<UserResponds> {
                override fun onFailure(call: Call<UserResponds>, t: Throwable) {
                    var responds= UserResponds()
                    responds.status = false
                    responds.timeout = true
                    loginLiveData.value = responds
                }

                override fun onResponse(
                    call: Call<UserResponds>,
                    response: Response<UserResponds>
                ) {
                    //http://13.232.205.158:3001/login
                    var responds:UserResponds
                    if(response.code()==200){
                        responds = response.body()!!
                        responds.status=true
                        loginLiveData.value = responds
                    }else{
                        responds= UserResponds()

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Log.i("errorBody", jObjError.toString())
                            //responds.errorCode = jObjError.getInt("error_code")
                            responds.message = jObjError.getString("message")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //responds = response.errorBody()
                        responds.status=false
                        loginLiveData.value = responds
                    }

                }
            })

        return loginLiveData
    }
    fun resendOTP(request: OtpRequest):MutableLiveData<UserResponds>{
        val loginLiveData = MutableLiveData<UserResponds>()

        Api.getClient()!!.create(Network::class.java).resend(request)
            .enqueue(object : Callback<UserResponds> {
                override fun onFailure(call: Call<UserResponds>, t: Throwable) {
                    var responds= UserResponds()
                    responds.status = false
                    responds.timeout = true
                    loginLiveData.value = responds
                }

                override fun onResponse(
                    call: Call<UserResponds>,
                    response: Response<UserResponds>
                ) {
                    //http://13.232.205.158:3001/login
                    var responds:UserResponds
                    if(response.code()==200){
                        responds = response.body()!!
                        responds.status=true
                        loginLiveData.value = responds
                    }else{
                        responds= UserResponds()

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Log.i("errorBody", jObjError.toString())
                            //responds.errorCode = jObjError.getInt("error_code")
                            responds.message = jObjError.getString("message")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //responds = response.errorBody()
                        responds.status=false
                        loginLiveData.value = responds
                    }

                }
            })

        return loginLiveData
    }
    fun updateProfile(request: ProfileRequest):MutableLiveData<UserResponds>{
        val loginLiveData = MutableLiveData<UserResponds>()

        Api.getClient()!!.create(Network::class.java).profile(request)
            .enqueue(object : Callback<UserResponds> {
                override fun onFailure(call: Call<UserResponds>, t: Throwable) {
                    var responds= UserResponds()
                    responds.status = false
                    responds.timeout = true
                    loginLiveData.value = responds
                }

                override fun onResponse(
                    call: Call<UserResponds>,
                    response: Response<UserResponds>
                ) {
                    //http://13.232.205.158:3001/login
                    var responds:UserResponds
                    if(response.code()==200){
                        responds = response.body()!!
                        responds.status=true
                        loginLiveData.value = responds
                    }else{
                        responds= UserResponds()

                        try {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            Log.i("errorBody", jObjError.toString())
                            //responds.errorCode = jObjError.getInt("error_code")
                            responds.message = jObjError.getString("message")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        //responds = response.errorBody()
                        responds.status=false
                        loginLiveData.value = responds
                    }

                }
            })

        return loginLiveData
    }
}