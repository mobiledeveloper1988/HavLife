/*
 * Created by Jithin kozhiyodan on 13/1/21 6:20 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 1/1/21 3:51 PM
 *
 */

package com.hav.havlife.view.fragment.dashboard.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hav.havlife.R
import com.hav.havlife.view.adapter.dashboard.activity.DailyActivityRowAdapter
import kotlinx.android.synthetic.main.fragment_daily_activity.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyActivity.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyActivity : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var dailyActivityRowAdapter: DailyActivityRowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_daily_activity, container, false)

        setAdapter(view)
        return view


    }
    private fun setAdapter(view:View) {
        //
        var linearLayoutManagerEarning = LinearLayoutManager(requireContext())
        view.rcvDailyActivity!!.layoutManager = linearLayoutManagerEarning
        view.rcvDailyActivity!!.itemAnimator = DefaultItemAnimator()
        dailyActivityRowAdapter = DailyActivityRowAdapter(requireContext())
        view.rcvDailyActivity!!.adapter = dailyActivityRowAdapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DailyActivity.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyActivity().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}