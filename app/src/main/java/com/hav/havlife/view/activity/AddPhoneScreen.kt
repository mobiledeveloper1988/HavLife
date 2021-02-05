/*
 * Created by Jithin kozhiyodan on 18/10/20 10:29 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 18/10/20 10:29 PM
 *
 */

package com.hav.havlife.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.hav.havlife.BaseActivity
import com.hav.havlife.R
import com.hbb20.CountryCodePicker
import kotlinx.android.synthetic.main.activity_add_phone_screen.*

class AddPhoneScreen : BaseActivity(), CountryCodePicker.OnCountryChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_phone_screen)
        setSupportActionBar(topAppBarAddPhone)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        topAppBarAddPhone.setNavigationOnClickListener { onBackPressed() }
        supportActionBar!!.title = " "


        countryCodePicker!!.setOnCountryChangeListener(this)
        btnAddPhone.setOnClickListener {
            val intent = Intent(this, PhoneConfirmationScreen::class.java)
            startActivity(intent)

        }
    }

    override fun onCountrySelected() {
//        countryCode=ccp!!.selectedCountryCode
//        countryName=ccp!!.selectedCountryName

        Toast.makeText(this,"Country Code "+countryCodePicker.selectedCountryCode,Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"Country Name "+countryCodePicker.selectedCountryName,Toast.LENGTH_SHORT).show()
    }
}
