/*
 * Created by Jithin kozhiyodan on 13/1/21 5:54 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 13/1/21 5:54 PM
 *
 */

package com.hav.havlife.view.fragment.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.isConnectedToNetwork
import com.hav.havlife.model.responds_data.login.UserResponds
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.adapter.dashboard.DashBoardActivityPageAdapter
import com.hav.havlife.view.adapter.dashboard.EarningRowAdapter
import com.hav.havlife.view.adapter.dashboard.FuelStartRowAdapter
import com.hav.havlife.view.adapter.dashboard.UpcomingChallengeRowAdapter
import com.hav.havlife.view.custom.CustomViewpager
import com.hav.havlife.view.fragment.ProfileCompletionFinish
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_sign_in_screen.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var earningRowAdapter: EarningRowAdapter
    private lateinit var fuelStartRowAdapter: FuelStartRowAdapter
    private lateinit var upcomingChallengeRowAdapter: UpcomingChallengeRowAdapter
    private lateinit var adapter: DashBoardActivityPageAdapter

    private var mActivityViewpager: CustomViewpager? = null
    private var activityTabLayout: TabLayout? = null

    private lateinit var rcvEarningPoints:RecyclerView
    private lateinit var rcvFuelStart:RecyclerView
    private lateinit var rcvChallenge:RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initView(root)
        setAdapter()
        return root
    }

    private fun initView(view:View){
        mActivityViewpager = view.mActivityViewpager
        activityTabLayout = view.activityTabLayout

        rcvEarningPoints = view.rcvEarningPoints
        rcvFuelStart = view.rcvFuelStart
        rcvChallenge = view.rcvChallenge


    }
    private fun setAdapter() {
        adapter = DashBoardActivityPageAdapter(childFragmentManager)
        mActivityViewpager!!.adapter = adapter
        activityTabLayout!!.setupWithViewPager(mActivityViewpager)

        //earnings
        var linearLayoutManagerEarning = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcvEarningPoints!!.layoutManager = linearLayoutManagerEarning
        rcvEarningPoints!!.itemAnimator = DefaultItemAnimator()
        earningRowAdapter = EarningRowAdapter(requireActivity())
        rcvEarningPoints!!.adapter = earningRowAdapter

        //fuel start
        var linearLayoutManagerFuel = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcvFuelStart!!.layoutManager = linearLayoutManagerFuel
        rcvFuelStart!!.itemAnimator = DefaultItemAnimator()
        fuelStartRowAdapter = FuelStartRowAdapter(requireActivity())
        rcvFuelStart!!.adapter = fuelStartRowAdapter

        //fuel start
        var linearLayoutManagerChallenge = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcvChallenge!!.layoutManager = linearLayoutManagerChallenge
        rcvChallenge!!.itemAnimator = DefaultItemAnimator()
        upcomingChallengeRowAdapter = UpcomingChallengeRowAdapter(requireActivity())
        rcvChallenge!!.adapter = upcomingChallengeRowAdapter
    }

    private fun onLoad() {
        if (requireActivity().isConnectedToNetwork()) {
            //relLoader.visibility = View.VISIBLE
            updateProfile()
        }else{

            Snackbar.make(cstSign, R.string.txt_internet_error, Snackbar.LENGTH_SHORT).show()
        }
    }
    private fun updateProfile(){
        homeViewModel.getHomeData()
            .observe(requireActivity(), Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {

//                        val intent = Intent(this, ProfileCompletionContainer::class.java)
//                        startActivity(intent)
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
}