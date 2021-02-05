/*
 * Created by Jithin kozhiyodan on 30/12/20 7:44 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/12/20 7:44 PM
 *
 */

package com.hav.havlife.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hav.havlife.R
import com.hav.havlife.view.adapter.dashboard.DashBoardActivityPageAdapter
import com.hav.havlife.view.adapter.dashboard.EarningRowAdapter
import com.hav.havlife.view.adapter.dashboard.FuelStartRowAdapter
import com.hav.havlife.view.adapter.dashboard.UpcomingChallengeRowAdapter
import kotlinx.android.synthetic.main.activity_dashboard.*

class Dashboard : AppCompatActivity() {
    private lateinit var earningRowAdapter: EarningRowAdapter
    private lateinit var fuelStartRowAdapter: FuelStartRowAdapter
    private lateinit var upcomingChallengeRowAdapter: UpcomingChallengeRowAdapter
    private lateinit var adapter: DashBoardActivityPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        nstScrollStepTwo.isFillViewport = true
//        setAdapter()
    }
//    private fun setAdapter() {
//        adapter = DashBoardActivityPageAdapter(supportFragmentManager)
//        mActivityViewpager!!.adapter = adapter
//        activityTabLayout!!.setupWithViewPager(mActivityViewpager)
//
//        //earnings
//        var linearLayoutManagerEarning = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rcvEarningPoints!!.layoutManager = linearLayoutManagerEarning
//        rcvEarningPoints!!.itemAnimator = DefaultItemAnimator()
//        earningRowAdapter = EarningRowAdapter(this@Dashboard)
//        rcvEarningPoints!!.adapter = earningRowAdapter
//
//        //fuel start
//        var linearLayoutManagerFuel = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rcvFuelStart!!.layoutManager = linearLayoutManagerFuel
//        rcvFuelStart!!.itemAnimator = DefaultItemAnimator()
//        fuelStartRowAdapter = FuelStartRowAdapter(this@Dashboard)
//        rcvFuelStart!!.adapter = fuelStartRowAdapter
//
//        //fuel start
//        var linearLayoutManagerChallenge = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        rcvChallenge!!.layoutManager = linearLayoutManagerChallenge
//        rcvChallenge!!.itemAnimator = DefaultItemAnimator()
//        upcomingChallengeRowAdapter = UpcomingChallengeRowAdapter(this@Dashboard)
//        rcvChallenge!!.adapter = upcomingChallengeRowAdapter
//    }
}