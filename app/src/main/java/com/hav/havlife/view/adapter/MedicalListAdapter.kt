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
import com.hav.havlife.view.fragment.ProfileCompletionStepSeven
import kotlinx.android.synthetic.main.content_medical_row.view.*

class MedicalListAdapter(context: Context, itemMedical: ItemMedical) :
    RecyclerView.Adapter<MedicalListAdapter.ViewHolder>() {
    val mContext = context
    val mItemMedical = itemMedical
    var isSelectedPos = -1
    val imgArray = arrayOf(
        R.drawable.ic_diabetic, R.drawable.ic_thyroid, R.drawable.ic_cholesterol,
        R.drawable.ic_hypertension, R.drawable.ic_stress
    )
    val nameArray = arrayOf(
        "Diabetic", "Thyroid", "Cholesterol",
        "Hypertension", "Stress"
    )
    val nameSubArray = arrayOf(
        "High sugar in the blood",
        "The thyroid is a gland problem",
        "Cholesterol is a waxy substance",
        "Hypertension (HTN or HT)",
        "Stress is a feeling of emotional"
    )

    interface ItemMedical {
        fun click(data: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh: ViewHolder
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_medical_row, parent, false)
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

        holder.binding.chkSelection.isChecked = isSelectedPos == position
        holder.binding.chkSelection.setOnClickListener {
            if (holder.binding.chkSelection.isChecked) {
                isSelectedPos = position
                mItemMedical.click(nameArray[position])
            } else {
                isSelectedPos = -1
                mItemMedical.click("")
            }
            notifyDataSetChanged()
        }

    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}