/*
 * Created by Jithin kozhiyodan on 13/1/21 6:20 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 2/1/21 9:04 AM
 *
 */

package com.hav.havlife.view.fragment.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hav.havlife.R
import com.hav.havlife.view.adapter.dashboard.intake.DailyInTakeRowAdapter
import kotlinx.android.synthetic.main.fragment_daily_intake.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DailyIntake.newInstance] factory method to
 * create an instance of this fragment.
 */
class DailyIntake : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var dailyInTakeRowAdapter: DailyInTakeRowAdapter
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
        val view=inflater.inflate(R.layout.fragment_daily_intake, container, false)
        setAdapter(view)
        return view
    }
    private fun setAdapter(view:View) {
        //
        var linearLayoutManagerEarning = LinearLayoutManager(requireContext())
        view.rcvBreakfastIntake!!.layoutManager = linearLayoutManagerEarning
        view.rcvBreakfastIntake!!.itemAnimator = DefaultItemAnimator()
        dailyInTakeRowAdapter = DailyInTakeRowAdapter(requireContext())
        view.rcvBreakfastIntake!!.adapter = dailyInTakeRowAdapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DailyIntake.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DailyIntake().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}