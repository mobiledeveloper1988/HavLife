/*
 * Created by Jithin kozhiyodan on 13/1/21 5:54 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 13/1/21 5:54 PM
 *
 */

package com.hav.havlife.view.fragment.dashboard.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hav.havlife.data.communication.repositry.dashboard.HomeRepo
import com.hav.havlife.data.communication.repositry.login.UserRepo
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.responds_data.login.UserResponds

class HomeViewModel : ViewModel() {
    private var homeRepo: HomeRepo? = null
    init {
        homeRepo = HomeRepo()
    }

    fun getHomeData(): MutableLiveData<UserResponds> {
        Log.i("ViewModel", "i called getPastData")
        return homeRepo!!.homeData()
    }

}