/*
 * Created by Jithin kozhiyodan on 26/1/21 11:17 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 26/1/21 11:17 AM
 *
 */

package com.hav.havlife.data.data_util

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat

object CommonUtil {
    @Suppress("DEPRECATION")
    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    fun loadDateOfBirth(mDate: String): String {
        val format1 = SimpleDateFormat("dd-MM-yyyy")
        val format2 = SimpleDateFormat("dd-MMM-yyyy")
        val dob = format1.parse(mDate)
        return format2.format(dob)
    }
}