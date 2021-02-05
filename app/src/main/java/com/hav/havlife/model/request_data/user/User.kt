/*
 * Created by Jithin kozhiyodan on 2/2/21 8:56 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/2/21 8:56 PM
 *
 */

package com.hav.havlife.model.request_data.user

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class User(@SerializedName("_id")
                @Expose
                val id: String? = "",

                @SerializedName("name")
                @Expose
                val name: String? = "",

                @SerializedName("weight")
                @Expose
                val weight: Weight? = null) {}