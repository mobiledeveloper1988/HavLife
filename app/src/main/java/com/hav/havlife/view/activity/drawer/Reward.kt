/*
 * Created by Jithin kozhiyodan on 15/1/21 7:21 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 15/1/21 7:21 PM
 *
 */

package com.hav.havlife.view.activity.drawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hav.havlife.R
import com.hav.havlife.view.adapter.ReferralPageAdapter
import com.hav.havlife.view.adapter.RewardPageAdapter
import kotlinx.android.synthetic.main.activity_referral.*

class Reward : AppCompatActivity() {
    private lateinit var adapter: RewardPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)
        setAdapter()
    }
    private fun setAdapter() {
        adapter = RewardPageAdapter(supportFragmentManager)
        viewPager!!.adapter = adapter
        activityTabLayout!!.setupWithViewPager(viewPager)
    }
}