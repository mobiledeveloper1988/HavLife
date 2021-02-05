/*
 * Created by Jithin kozhiyodan on 2/2/21 8:50 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/2/21 8:44 PM
 *
 */

package com.hav.havlife.model.request_data.dashboard

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.hav.havlife.model.request_data.user.User


data class DashBoard(
    @SerializedName("user")
    @Expose
    val user: User? = null,

    @SerializedName("scoreBoard")
    @Expose
    val scoreBoard: ScoreBoard? = null
) {}