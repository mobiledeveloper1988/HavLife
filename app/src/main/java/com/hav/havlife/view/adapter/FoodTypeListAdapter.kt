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
import com.hav.havlife.view.fragment.ProfileCompletionStepNine
import kotlinx.android.synthetic.main.content_food_like_row.view.*
import kotlinx.android.synthetic.main.content_food_type_row.view.*
import kotlinx.android.synthetic.main.content_medical_row.view.*
import kotlinx.android.synthetic.main.content_medical_row.view.imgMedical
import kotlinx.android.synthetic.main.content_medical_row.view.txtMedicalHead
import kotlinx.android.synthetic.main.content_medical_row.view.txtMedicalSub

class FoodTypeListAdapter(context: Context, itemFoodType: ItemFoodType) :
    RecyclerView.Adapter<FoodTypeListAdapter.ViewHolder>() {
    val mContext = context
    var mItemFoodType = itemFoodType
    val imgArray = arrayOf(R.drawable.ic_vegitarian, R.drawable.ic_non_veg)
    val nameArray = arrayOf("Vegetarian", "Non-Vegetarian")
    val nameSubArray = arrayOf("High sugar in the blood", "The thyroid is a gland problem")
    var isSelectedPos = -1

    interface ItemFoodType {
        fun clickFoodType(data: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh: ViewHolder
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_food_type_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return imgArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgMedical.setImageResource(imgArray[position])
        holder.binding.txtMedicalHead.text = nameArray[position]
        holder.binding.txtMedicalSub.text = nameSubArray[position]
        holder.binding.chkFoodType.isChecked = isSelectedPos == position
        holder.binding.chkFoodType.setOnClickListener {
            if (holder.binding.chkFoodType.isChecked) {
                isSelectedPos = position
                mItemFoodType.clickFoodType(nameArray[position])
            } else {
                isSelectedPos = -1
                mItemFoodType.clickFoodType("")
            }
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}