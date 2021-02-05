/*
 * Created by Jithin kozhiyodan on 30/12/20 5:20 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/12/20 5:20 PM
 *
 */

package com.hav.havlife.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.Dashboard
import com.hav.havlife.view.activity.ProfileCompletionContainer
import kotlinx.android.synthetic.main.fragment_profile_completion_finish.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionFinish.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionFinish : Fragment() {
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
        val view= inflater.inflate(R.layout.fragment_profile_completion_finish, container, false)

        view.btnLetsStart.setOnClickListener {
            requireActivity().startActivity(Intent(requireContext(),Dashboard::class.java))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as ProfileCompletionContainer).hideToolbar()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileCompletionFinsh.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionFinish().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}