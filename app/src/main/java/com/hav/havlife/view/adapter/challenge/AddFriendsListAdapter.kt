/*
 * Created by Jithin kozhiyodan on 30/11/20 9:08 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/11/20 9:08 PM
 *
 */

package com.hav.havlife.view.adapter.challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R
import kotlinx.android.synthetic.main.content_interest_row.view.*
import kotlinx.android.synthetic.main.content_interest_row.view.imgInterest
import kotlinx.android.synthetic.main.content_medical_row.view.*
import java.util.ArrayList

class AddFriendsListAdapter(context: Context) :
    RecyclerView.Adapter<AddFriendsListAdapter.ViewHolder>() {
val mContext = context

    val imgArray = arrayOf(R.drawable.ic_vegitable,R.drawable.ic_fruits,R.drawable.ic_wheat,
        R.drawable.ic_meat,R.drawable.ic_dairy_products)
    val nameArray = arrayOf("Vegetables","Fruits","Grains, Beans and Nuts",
        "Meat and Paultry","Dairy Foods")
    val nameSubArray = arrayOf("High sugar in the blood","The thyroid is a gland problem","Cholesterol is a waxy substance",
        "Hypertension (HTN or HT)","Stress is a feeling of emotional")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vh: ViewHolder
                val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_friend_add_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.imgMedical.setImageResource(imgArray[position])
//        holder.binding.txtMedicalHead.text = nameArray[position]
        //holder.binding.txtMedicalSub.text = nameSubArray[position]

    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}