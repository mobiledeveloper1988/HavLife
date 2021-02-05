/*
 * Created by Jithin kozhiyodan on 25/1/21 11:47 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 25/1/21 11:47 AM
 *
 */

package com.hav.havlife.model.responds_data.login

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class UserResponds(@SerializedName("name")
                        @Expose
                        val name: String? = "",

                        @SerializedName("username")
                        @Expose
                        val username: String? = "",

                        @SerializedName("password")
                        @Expose
                        val password: String? = "",

                        @SerializedName("mobile")
                        @Expose
                        val mobile: String? = "",

                        @SerializedName("token")
                        @Expose
                        val token: String? = "",

                        @SerializedName("message")
                        @Expose
                        var message: String? = "",
var status:Boolean=false,
var timeout:Boolean=false) {}