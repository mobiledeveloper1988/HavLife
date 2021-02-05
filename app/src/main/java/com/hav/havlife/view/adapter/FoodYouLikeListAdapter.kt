/*
 * Created by Jithin kozhiyodan on 30/11/20 9:08 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 30/11/20 9:08 PM
 *
 */

package com.hav.havlife.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R
import kotlinx.android.synthetic.main.content_food_like_row.view.*
import java.util.ArrayList


class FoodYouLikeListAdapter(context: Context, itemFoodLike: ItemFoodLike) :
    RecyclerView.Adapter<FoodYouLikeListAdapter.ViewHolder>() {
val mContext = context

    val imgArray = arrayOf(R.drawable.ic_vegitable,R.drawable.ic_fruits,R.drawable.ic_wheat,
        R.drawable.ic_meat,R.drawable.ic_dairy_products)
    val nameArray = arrayOf("Vegetables","Fruits","Grains, Beans and Nuts",
        "Meat and Paultry","Dairy Foods")
    val nameSubArray = arrayOf("High sugar in the blood","The thyroid is a gland problem","Cholesterol is a waxy substance",
        "Hypertension (HTN or HT)","Stress is a feeling of emotional")
    var isSelectedPos = -1
    private var selectedFood: ArrayList<String>? = ArrayList()
    var mItemFoodLike = itemFoodLike
    interface ItemFoodLike {
        fun clickFoodLike(data: ArrayList<String>?)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vh: ViewHolder
                val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_food_like_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return imgArray.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgMedical.setImageResource(imgArray[position])
        holder.binding.txtMedicalHead.text = nameArray[position]
        //holder.binding.txtMedicalSub.text = nameSubArray[position]

        holder.binding.chkFoodSelection.setOnClickListener {
            if (holder.binding.chkFoodSelection.isChecked) {
                isSelectedPos = position
                selectedFood!!.add(nameArray[position])
                mItemFoodLike.clickFoodLike(selectedFood)
            } else {
                isSelectedPos = -1
                selectedFood!!.removeIf { n -> n === nameArray[position] }
                mItemFoodLike.clickFoodLike(selectedFood)

            }
        }

    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}