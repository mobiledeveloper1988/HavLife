/*
 * Created by Jithin kozhiyodan on 25/1/21 10:29 AM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 21/10/20 7:06 PM
 *
 */

package com.hav.havlife.view.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hav.havlife.BaseActivity
import com.hav.havlife.HavLifeApp
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.isConnectedToNetwork
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.view_util.AppPattern.isValidEmail
import kotlinx.android.synthetic.main.activity_sign_in_screen.*

class SignInScreen : BaseActivity() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.activity_sign_in_screen)
        btnSignIn.setOnClickListener {

            if (!isValidEmail(edEmail!!.text!!.toString().trim())) {
                inputEmail!!.error = resources.getString(R.string.txt_email_error)
            } else if (edPassword.text.toString().length < 6) {
                inputPassword!!.error = resources.getString(R.string.txt_password_error)
            } else {
                val userRequest = LoginRequest()
                userRequest.username = edEmail.text.toString().trim()
                userRequest.password = edPassword.text.toString()
                onLoad(userRequest)
            }
        }
        setTextWatcher()
    }

    private fun onLoad(request: LoginRequest) {
        if (isConnectedToNetwork()) {
            relLoader.visibility = View.VISIBLE
            login(request)
        }else{

            Snackbar.make(cstSign, R.string.txt_internet_error, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun login(request: LoginRequest){
        userViewModel.getUserData(request)
            .observe(this, Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {
                        HavLifeApp.appPreference!!.setLogin(true)
                        val intent = Intent(this, ProfileCompletionContainer::class.java)
                        startActivity(intent)
                        Snackbar.make(cstSign, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    it.timeout -> {
                        Snackbar.make(cstSign, R.string.txt_server_error, Snackbar.LENGTH_SHORT).show()
                    }
                    else -> {
                        Snackbar.make(cstSign, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                }
            })

    }

    private fun setTextWatcher() {
        edEmail!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            //50800008
            override fun afterTextChanged(editable: Editable) {
                if (!isValidEmail(edEmail!!.text!!.toString().trim())) {
                    inputEmail!!.error = resources.getString(R.string.txt_email_error)

                } else {
                    inputEmail!!.error = null
                }
            }
        })

        edPassword!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            //50800008
            override fun afterTextChanged(editable: Editable) {
                if (edPassword.text.toString().length < 6) {
                    inputPassword!!.error = resources.getString(R.string.txt_password_error)

                } else {
                    inputPassword!!.error = null
                }
            }
        })
    }
}
