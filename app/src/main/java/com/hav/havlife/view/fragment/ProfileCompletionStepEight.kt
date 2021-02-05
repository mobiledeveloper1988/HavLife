/*
 * Created by Jithin kozhiyodan on 8/12/20 12:53 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 8/12/20 12:52 AM
 *
 */

package com.hav.havlife.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.adapter.MindListAdapter
import com.hav.havlife.view.adapter.PhysicalListAdapter
import kotlinx.android.synthetic.main.fragment_profile_completion_step_eight.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepEight.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepEight : Fragment(),MindListAdapter.ItemMind,PhysicalListAdapter.ItemState {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var obj: ProfileRequest



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            obj = it.getSerializable("obj") as ProfileRequest
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_profile_completion_step_eight, container, false)
        view.btnStepEight.setOnClickListener {
            (activity as ProfileCompletionContainer).changeFragment(ProfileCompletionStepNine(),"ProfileCompletionStepNine")
        }
        adapter(view)
        return view

    }
    private fun adapter(view:View){

        var linearLayoutManagerPhysical = GridLayoutManager(activity, 3)
        view.rcvPhysicalState!!.layoutManager = linearLayoutManagerPhysical
        view.rcvPhysicalState!!.itemAnimator = DefaultItemAnimator()
        val physicalListAdapter =
            PhysicalListAdapter(requireActivity(),this)
        view.rcvPhysicalState!!.adapter = physicalListAdapter

        var linearLayoutManagerMind = GridLayoutManager(activity, 3)
        view.rcvMindState!!.layoutManager = linearLayoutManagerMind
        view.rcvMindState!!.itemAnimator = DefaultItemAnimator()
        val mindListAdapter =
            MindListAdapter(requireActivity(),this)
        view.rcvMindState!!.adapter = mindListAdapter
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionStepEight.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepEight().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun clickMind(data: String) {
obj.mentalState=data
    }

    override fun clickState(data: String) {
obj.physicalState=data
    }
}