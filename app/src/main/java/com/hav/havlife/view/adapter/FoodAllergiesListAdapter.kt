/*
 * Created by Jithin kozhiyodan on 30/11/20 9:08 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/11/20 9:08 PM
 *
 */

package com.hav.havlife.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R
import kotlinx.android.synthetic.main.content_medical_row.view.*

class FoodAllergiesListAdapter(context: Context) :
    RecyclerView.Adapter<FoodAllergiesListAdapter.ViewHolder>() {
val mContext = context

//    val imgArray = arrayOf(R.drawable.ic_diabetic,R.drawable.ic_thyroid,R.drawable.ic_cholesterol,
//        R.drawable.ic_hypertension,R.drawable.ic_stress)
    val nameArray = arrayOf("The thyroid is a gland problem","The thyroid is a gland problem","Diarrhea",
        "Hives","Itchy Rash")
    val nameSubArray = arrayOf("Enter food substances","Enter food substances","Enter food substances",
        "Enter food substances","Enter food substances")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vh: ViewHolder
                val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_allergies_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return nameArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.binding.imgMedical.setImageResource(imgArray[position])
        holder.binding.txtMedicalHead.text = nameArray[position]
        holder.binding.txtMedicalSub.text = nameSubArray[position]

    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}