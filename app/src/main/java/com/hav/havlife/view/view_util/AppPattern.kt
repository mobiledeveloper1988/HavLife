/*
 * Created by Jithin kozhiyodan on 21/10/20 6:52 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 21/10/20 6:48 PM
 *
 */

package com.hav.havlife.view.view_util

import android.text.TextUtils
import android.util.Patterns

object AppPattern {

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }
}