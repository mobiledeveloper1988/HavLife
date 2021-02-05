/*
 * Created by Jithin kozhiyodan on 30/11/20 8:56 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/11/20 8:56 PM
 *
 */

package com.hav.havlife.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.adapter.InterestListAdapter
import kotlinx.android.synthetic.main.fragment_profile_completion_step_two.view.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepTwo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepTwo : Fragment(), InterestListAdapter.ItemInterest {
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

        val view = inflater.inflate(R.layout.fragment_profile_completion_step_two, container, false)
        Log.i("name",obj.name)
        Log.i("last name",obj.lastName)
        Log.i("dob",obj.birthDate)
        Log.i("location",obj.location)
        var linearLayoutManagerDoctors = GridLayoutManager(activity, 3)
        view.rcvInterest!!.layoutManager = linearLayoutManagerDoctors
        view.rcvInterest!!.itemAnimator = DefaultItemAnimator()
        val interestListAdapter =
            InterestListAdapter(requireContext(),this)
        view.rcvInterest!!.adapter = interestListAdapter

        view.btnStepTwo.setOnClickListener {
            (activity as ProfileCompletionContainer).changeFragment(ProfileCompletionStepThree(),"ProfileCompletionStepThree")
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionStepTwo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun click(data: ArrayList<String>?) {
        Log.i("Interest","$data")
        obj.interests = data
    }
}