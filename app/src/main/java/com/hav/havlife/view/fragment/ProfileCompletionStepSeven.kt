/*
 * Created by Jithin kozhiyodan on 8/12/20 12:51 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 8/12/20 12:51 AM
 *
 */

package com.hav.havlife.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.adapter.MedicalListAdapter
import kotlinx.android.synthetic.main.fragment_profile_completion_step_seven.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepSeven.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepSeven : Fragment(), MedicalListAdapter.ItemMedical {
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
        val view =
            inflater.inflate(R.layout.fragment_profile_completion_step_seven, container, false)
        view.btnStepSeven.setOnClickListener {
            (activity as ProfileCompletionContainer).changeFragment(
                ProfileCompletionStepEight(),
                "ProfileCompletionStepSeven"
            )
        }
        adapter(view)
        return view

    }

    private fun adapter(view: View) {
        var linearLayoutManagerDoctors = LinearLayoutManager(activity)
        view.rcvMedicalCondition!!.layoutManager = linearLayoutManagerDoctors
        view.rcvMedicalCondition!!.itemAnimator = DefaultItemAnimator()
        val medicalListAdapter =
            MedicalListAdapter(requireActivity(), this)
        view.rcvMedicalCondition!!.adapter = medicalListAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionStepSeven.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepSeven().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun click(data: String) {
        obj.medicalCondition = data
    }
}