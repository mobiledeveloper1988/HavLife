/*
 * Created by Jithin kozhiyodan on 1/12/20 7:24 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 1/12/20 7:24 PM
 *
 */

package com.hav.havlife.view.fragment

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.activity.ProfileCompletionContainer
import com.hav.havlife.view.custom.OnScaleUpdate
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test.scroll
import kotlinx.android.synthetic.main.fragment_profile_completion_step_five.*
import kotlinx.android.synthetic.main.fragment_profile_completion_step_five.view.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileCompletionStepFive.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCompletionStepFive : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var obj: ProfileRequest
    var isCm=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            obj = it.getSerializable("obj") as ProfileRequest
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(
            R.layout.fragment_profile_completion_step_five,
            container,
            false
        )
        Log.i("name",obj.toString())
        if(obj.gender =="Male"){
            view.imgGirl.setImageResource(R.drawable.ic_boy)
        }else{
            view.imgGirl.setImageResource(R.drawable.ic_girl)
        }
        view.scaleCm.setScaleUpdate(object : OnScaleUpdate {
            override fun onScaleSelected(result: Float) {
                view.txtHeight.text = "$result cm"
            }
        })
        view.scaleInch.setScaleUpdate(object : OnScaleUpdate {
            override fun onScaleSelected(result: Float) {
                view.txtHeight.text = "$result Ft"
            }
        })
        view.scroll.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {
            val scrollY: Int = scroll.getScrollY() // For ScrollView
            val scrollX: Int = scroll.getScrollX() // For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
            Log.i(
                "scrollY", "$scrollY - ${scroll.x} X ${scroll.y}" +
                        " - ${view.scroll.left} X ${view.scroll.top} -" +
                        "${view.txtHeight.x} X ${view.txtHeight.y}" +
                        " -${view.txtHeight.left} X ${view.txtHeight.top}"
            )


            val cm = (scrollY / 100F) * 5 + 5
            val s = String.format("%.0f", cm)
            val e = 365 - s.toInt()


            val feet = (0.0328 * e)
            val fFormat = String.format("%.3f", feet)
            val c = fFormat.split(".")
            //val d = c[0].toInt() + c[1].toInt()

            val d = ".${c[1]}".toFloat()
            val inch = (d * 12)
            val iFormat = String.format("%.0f", inch).toInt()
//            val inch1 = iFormat/10

            Log.i("inch","d-:$d ,inch:-$inch ,iFormat:-$iFormat")

            if(isCm){
                view.txtHeight.text = "$e Cm "
            }else{
                view.txtHeight.text = "${c[0]} Ft $iFormat Inch"
            }
            //view.txtHeight.text = "$e Cm ${c[0]} Ft $iFormat Inch"

        })
        view.btnStepFive.setOnClickListener {
            (activity as ProfileCompletionContainer).changeFragment(
                ProfileCompletionStepSix(),
                "ProfileCompletionStepSix"
            )
        }
        view.btnCm.setOnClickListener {
            isCm=true
            view.btnCm.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorRed,null))
            view.btnCm.setTextColor(resources.getColor(R.color.colorWhite, null))
            view.btnInc.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorWhite,null))
            view.btnInc.setTextColor(resources.getColor(R.color.colorGray, null))
            view.scaleCm.visibility = View.VISIBLE
            view.scaleInch.visibility = View.GONE
        }
        view.btnInc.setOnClickListener {
            isCm=false
            view.btnCm.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorWhite,null))
            view.btnCm.setTextColor(resources.getColor(R.color.colorGray, null))
            view.btnInc.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorRed,null))
            view.btnInc.setTextColor(resources.getColor(R.color.colorWhite, null))
            view.scaleCm.visibility = View.GONE
            view.scaleInch.visibility = View.VISIBLE
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
         * @return A new instance of fragment ProfileCompletionStepFive.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileCompletionStepFive().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}