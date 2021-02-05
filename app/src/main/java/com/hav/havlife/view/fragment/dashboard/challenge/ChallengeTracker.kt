/*
 * Created by Jithin kozhiyodan on 13/1/21 6:04 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 13/1/21 3:54 PM
 *
 */

package com.hav.havlife.view.fragment.dashboard.challenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R
import com.hav.havlife.view.adapter.challenge.LevelRowAdapter
import kotlinx.android.synthetic.main.fragment_challenge_tracker.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChallengeTracker.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChallengeTracker : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var rcvChallenge: RecyclerView
    private lateinit var levelRowAdapter: LevelRowAdapter
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
        val view =inflater.inflate(R.layout.fragment_challenge_tracker, container, false)

        initView(view)
        setAdapter()
        return view
    }
    private fun initView(view:View) {
        rcvChallenge = view.rcvChallengeTracker


    }
    private fun setAdapter() {//challenge
        var linearLayoutManagerEarning = LinearLayoutManager(requireContext())
        rcvChallenge.layoutManager = linearLayoutManagerEarning
        rcvChallenge.itemAnimator = DefaultItemAnimator()
        levelRowAdapter = LevelRowAdapter(requireContext())
        rcvChallenge.adapter = levelRowAdapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChallengeTracker.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChallengeTracker().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}