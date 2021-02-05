/*
 * Created by Jithin kozhiyodan on 13/1/21 6:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 13/1/21 3:34 PM
 *
 */

package com.hav.havlife.view.fragment.dashboard.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.hav.havlife.R
import com.hav.havlife.view.adapter.challenge.ChallengePagerAdapter
import com.hav.havlife.view.custom.CustomViewpager
import kotlinx.android.synthetic.main.fragment_challenge.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChallengeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChallengeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var mActivityViewpager: CustomViewpager? = null
    private var activityTabLayout: TabLayout? = null
    private lateinit var adapter: ChallengePagerAdapter

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
        val view = inflater.inflate(R.layout.fragment_challenge, container, false)

        initView(view)
        setAdapter()
        return view
    }

    private fun initView(view:View) {
        mActivityViewpager = view.mActivityViewpager
        activityTabLayout = view.activityTabLayout

    }
    private fun setAdapter() {//challenge
        adapter = ChallengePagerAdapter(requireContext(),childFragmentManager)
        mActivityViewpager!!.adapter = adapter
        activityTabLayout!!.setupWithViewPager(mActivityViewpager)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChallengeHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChallengeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}