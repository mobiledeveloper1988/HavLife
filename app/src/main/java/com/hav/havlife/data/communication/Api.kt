/*
 * Created by Jithin kozhiyodan on 25/1/21 11:51 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 25/1/21 11:51 AM
 *
 */

package com.hav.havlife.data.communication

import com.google.gson.GsonBuilder
import com.hav.havlife.data.communication.ConstantData.baseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private var retrofit:Retrofit?=null


    @JvmStatic
    fun getClient():Retrofit?{
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build()
        val gson = GsonBuilder().setLenient().create()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)

                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}