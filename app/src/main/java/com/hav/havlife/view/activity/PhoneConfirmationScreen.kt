/*
 * Created by Jithin kozhiyodan on 18/10/20 10:30 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 18/10/20 10:30 PM
 *
 */

package com.hav.havlife.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hav.havlife.BaseActivity
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.isConnectedToNetwork
import com.hav.havlife.model.request_data.login.OtpRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import com.hav.havlife.view.activity.login.UserViewModel
import kotlinx.android.synthetic.main.activity_phone_confirmation_screen.*

class PhoneConfirmationScreen : BaseActivity() {
    var otpSend =""
    private lateinit var userViewModel: UserViewModel
    var email=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        email = intent.getStringExtra("email")
        setContentView(R.layout.activity_phone_confirmation_screen)
        setSupportActionBar(topAppBarConfirmPhone)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        topAppBarConfirmPhone.setNavigationOnClickListener { onBackPressed() }
        supportActionBar!!.title = " "
        regUI()
        btnConfirmPhone.setOnClickListener {
            load(1)
        }
        txtConfirmPhoneResend.setOnClickListener {
            load(2)
        }
    }

    inner class EditTextListener(view: View) : TextWatcher {
        private val mView = view
        override fun afterTextChanged(s: Editable?) {
            val otp = s.toString()
            //txtOtpErrorText.visibility = View.GONE
            when (mView.id) {

                R.id.edOtpCode1 -> {
                    if (otp.length == 1) {
                        edOtpCode2.requestFocus()
                    }
                }
                R.id.edOtpCode2 -> {
                    if (otp.length == 1) {
                        edOtpCode3.requestFocus()
                    } else {
                        edOtpCode1.requestFocus()
                    }
                }
                R.id.edOtpCode3 -> {
                    if (otp.length == 1) {
                        edOtpCode4.requestFocus()
                    } else {
                        edOtpCode2.requestFocus()
                    }
                }
                R.id.edOtpCode4 -> {
                    if (otp.length == 1) {
                        edOtpCode5.requestFocus()
                    } else {
                        edOtpCode3.requestFocus()
                    }
                }
                R.id.edOtpCode5 -> {
                    if (otp.length == 1) {
                        edOtpCode6.requestFocus()
                    } else {
                        edOtpCode4.requestFocus()
                    }
                }
                R.id.edOtpCode6 -> {
                    if (otp.isEmpty()) {
                        edOtpCode5.requestFocus()
                        val snackbar = Snackbar
                            .make(
                                cstOtp,
                                "Please enter 6-digit verification code ",
                                Snackbar.LENGTH_LONG
                            )
                        snackbar.show()

                        //Toast.makeText(this@ForgotPassword,"Please enter 4-digit verification code",Toast.LENGTH_SHORT).show()
                    } else {
                        closeKeyBoard()
                        otpSend = "${edOtpCode1.text}${edOtpCode2.text}${edOtpCode3.text}${edOtpCode4.text}${edOtpCode5.text}${edOtpCode6.text}"



                    }
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun regUI() {
        edOtpCode1.addTextChangedListener(EditTextListener(edOtpCode1))
        edOtpCode2.addTextChangedListener(EditTextListener(edOtpCode2))
        edOtpCode3.addTextChangedListener(EditTextListener(edOtpCode3))
        edOtpCode4.addTextChangedListener(EditTextListener(edOtpCode4))
        edOtpCode5.addTextChangedListener(EditTextListener(edOtpCode5))
        edOtpCode6.addTextChangedListener(EditTextListener(edOtpCode6))

    }

    private fun load(type:Int) {
        if (isConnectedToNetwork()) {
            relLoader.visibility = View.VISIBLE
            if(type==1)
                confirm()
            else
                resend()
        } else {

        }
    }
    private fun confirm() {
        val request=OtpRequest()
        request.confirmationcode = otpSend
        request.username = email
        userViewModel.verifyOTP(request).observe(this, Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {
                        val intent = Intent(this, PhoneConfirmationScreen::class.java)
                        startActivity(intent)
                        Snackbar.make(cstOtp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    it.timeout -> {
                        Snackbar.make(cstOtp, R.string.txt_server_error, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        Snackbar.make(cstOtp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                }
            })

    }
    private fun resend() {
        val request=OtpRequest()
        request.username = email
        userViewModel.resendOTP(request)
            .observe(this, Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {
                        val intent = Intent(this, PhoneConfirmationScreen::class.java)
                        startActivity(intent)
                        Snackbar.make(cstOtp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                    it.timeout -> {
                        Snackbar.make(cstOtp, R.string.txt_server_error, Snackbar.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        Snackbar.make(cstOtp, it.message!!, Snackbar.LENGTH_SHORT).show()
                    }
                }
            })

    }
}
