/*
 * Created by Jithin kozhiyodan on 15/1/21 1:42 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 15/1/21 1:42 PM
 *
 */

package com.hav.havlife.view.activity.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hav.havlife.R
import com.hav.havlife.view.adapter.ReferralPageAdapter
import kotlinx.android.synthetic.main.activity_referral.*


class Referral : AppCompatActivity() {
    private lateinit var adapter: ReferralPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_referral)
        setAdapter()
    }
    private fun setAdapter() {
        adapter = ReferralPageAdapter(supportFragmentManager)
        viewPager!!.adapter = adapter
        activityTabLayout!!.setupWithViewPager(viewPager)
    }
}