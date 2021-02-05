package com.hav.havlife.data.data_util

import android.content.Context
import android.content.SharedPreferences

class AppPreference (context: Context) {
    private val mContext = context
    var sharedPreferences: SharedPreferences =
        mContext.getSharedPreferences("hav_life", Context.MODE_PRIVATE)

    fun setLogin(isLogin:Boolean){
        val edit = sharedPreferences.edit()
        edit.putBoolean("isLogin",isLogin)
        edit.commit()
    }
    fun getLogin():Boolean{
        return sharedPreferences.getBoolean("isLogin", false)
    }
}