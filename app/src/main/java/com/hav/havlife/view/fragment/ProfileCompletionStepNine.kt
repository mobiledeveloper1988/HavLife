/*
 * Created by Jithin kozhiyodan on 8/12/20 12:53 AM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 8/12/20 12:53 AM
 *
 */

package com.hav.havlife.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hav.havlife.HavLifeApp
import com.hav.havlife.R
import com.hav.havlife.data.data_util.CommonUtil.isConnectedToNetwork
import com.hav.havlife.model.request_data.login.LoginRequest
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.model.responds_data.login.UserResponds
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.activity.login.UserViewModel
import com.hav.havlife.view.adapter.*
import kotlinx.android.synthetic.main.activity_sign_in_screen.*
import kotlinx.android.synthetic.main.fragment_profile_completion_step_nine.view.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepNine.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepNine : Fragment(), FoodTypeListAdapter.ItemFoodType,
    FoodYouLikeListAdapter.ItemFoodLike {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var obj: ProfileRequest
    private lateinit var userViewModel: UserViewModel
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

        val view= inflater.inflate(R.layout.fragment_profile_completion_step_nine, container, false)
        userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        view.btnStepNine.setOnClickListener {
            onLoad()

        }
//        view.btnStepEight.setOnClickListener {
//            (activity as ProfileCompletionContainer).changeFragment(ProfileCompletionFinish(),"ProfileCompletionFinish")
//        }
        adapter(view)
        return view
    }
    private fun adapter(view:View){

        var linearLayoutFoodType = LinearLayoutManager(activity)
        view.rcvFoodType!!.layoutManager = linearLayoutFoodType
        view.rcvFoodType!!.itemAnimator = DefaultItemAnimator()
        val foodTypeListAdapter =
            FoodTypeListAdapter(requireActivity(),this)
        view.rcvFoodType!!.adapter = foodTypeListAdapter

//        var linearLayoutFoodAllergie = LinearLayoutManager(activity)
//        view.rcvFoodAllergies!!.layoutManager = linearLayoutFoodAllergie
//        view.rcvFoodAllergies!!.itemAnimator = DefaultItemAnimator()
//        val foodAllergiesListAdapter =
//            FoodAllergiesListAdapter(requireActivity())
//        view.rcvFoodAllergies!!.adapter = foodAllergiesListAdapter

        var linearLayoutManagerMind = LinearLayoutManager(activity)
        view.rcvFoodYouLike!!.layoutManager = linearLayoutManagerMind
        view.rcvFoodYouLike!!.itemAnimator = DefaultItemAnimator()
        val foodYouLikeListAdapter =
            FoodYouLikeListAdapter(requireActivity(),this)
        view.rcvFoodYouLike!!.adapter = foodYouLikeListAdapter
    }
    private fun onLoad() {
        if (requireActivity().isConnectedToNetwork()) {
            relLoader.visibility = View.VISIBLE
            updateProfile()
        }else{

            Snackbar.make(cstSign, R.string.txt_internet_error, Snackbar.LENGTH_SHORT).show()
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionStepNine.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepNine().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun clickFoodType(data: String) {

        obj.foodPreference!!.diet = data
    }

    override fun clickFoodLike(data: ArrayList<String>?) {
        obj.foodPreference!!.favoriteFood = data
    }
    private fun updateProfile(){
        userViewModel.updateProfile(obj)
            .observe(requireActivity(), Observer<UserResponds> {
                relLoader.visibility = View.GONE
                when {
                    it.status -> {
                        (activity as ProfileCompletionContainer).changeFragment(ProfileCompletionFinish(),"ProfileCompletionFinish")
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