/*
 * Created by Jithin kozhiyodan on 27/1/21 12:08 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 21/10/20 4:26 PM
 *
 */

package com.hav.havlife.view.activity.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hav.havlife.BaseActivity
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.isConnectedToNetwork
import com.hav.havlife.model.request_data.login.RegisterRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import com.hav.havlife.view.activity.AddPhoneScreen
import com.hav.havlife.view.activity.PhoneConfirmationScreen
import com.hav.havlife.view.view_util.AppPattern.isValidEmail
import kotlinx.android.synthetic.main.activity_sign_up_screen.*
import kotlinx.android.synthetic.main.activity_sign_up_screen.edEmail
import kotlinx.android.synthetic.main.activity_sign_up_screen.edPassword
import kotlinx.android.synthetic.main.activity_sign_up_screen.inputEmail
import kotlinx.android.synthetic.main.activity_sign_up_screen.inputPassword

class SignUpScreen : BaseActivity() {
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        setContentView(R.layout.activity_sign_up_screen)
        setSupportActionBar(topAppBarSignUp)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        topAppBarSignUp.setNavigationOnClickListener { onBackPressed() }
        supportActionBar!!.title = " "

        btnSignUp.setOnClickListener {
            if (TextUtils.isEmpty(edName.text.toString().trim())) {
                inputName!!.error = resources.getString(R.string.txt_name_error)
            } else if (edMobile.text.toString().trim().length < 10) {
                inputMobile!!.error = resources.getString(R.string.txt_mobile_error)
            } else if (!isValidEmail(edEmail!!.text!!.toString().trim())) {
                inputEmail!!.error = resources.getString(R.string.txt_email_error)
            } else if (edPassword.text.toString().length < 6) {
                inputPassword!!.error = resources.getString(R.string.txt_password_error)
            } else if (edRePassword.text.toString() != edPassword.text.toString()) {
                inputRePassword!!.error = resources.getString(R.string.txt_confirm_password_error)
            } else {
                val request = RegisterRequest()
                request.name = edName.text.toString().trim()
                request.email = edEmail.text.toString()
                request.phonenumber = edMobile.text.toString()
                request.password = edPassword.text.toString()
                request.confirmpassword = edRePassword.text.toString()
                onLoad(request)
            }
        }
    }

    private fun onLoad(request: RegisterRequest) {
        if (isConnectedToNetwork()) {
            relLoader.visibility = View.VISIBLE
            register(request)
        } else {

            Snackbar.make(cstSignUp, R.string.txt_internet_error, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun register(request: RegisterRequest) {
        userViewModel.setUserData(request)
            .observe(this, Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {
                        val intent = Intent(this, PhoneConfirmationScreen::class.java).putExtra("email",it.username)
                        startActivity(intent)
                        Snackbar.make(cstSignUp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    it.timeout -> {
                        Snackbar.make(cstSignUp, R.string.txt_server_error, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        Snackbar.make(cstSignUp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                }
            })

    }
}
