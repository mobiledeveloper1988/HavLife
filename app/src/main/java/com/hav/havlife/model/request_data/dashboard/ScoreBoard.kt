/*
 * Created by Jithin kozhiyodan on 2/2/21 8:59 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/2/21 8:59 PM
 *
 */

package com.hav.havlife.model.request_data.dashboard

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class ScoreBoard(@SerializedName("_id")
                      @Expose
                      val id: String? = "",

                      @SerializedName("healthScore")
                      @Expose
                      val healthScore: Int? = 0,

                      @SerializedName("badges")
                      @Expose
                      val badges: Int? = 0,

                      @SerializedName("gems")
                      @Expose
                      val gems: Int? = 0,

                      @SerializedName("coins")
                      @Expose
                      val coins: Int? = 0) {}