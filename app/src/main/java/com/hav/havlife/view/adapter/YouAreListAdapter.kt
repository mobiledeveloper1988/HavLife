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
import com.hav.havlife.view.fragment.ProfileCompletionStepFour
import kotlinx.android.synthetic.main.content_interest_row.view.*

class YouAreListAdapter(context: Context, itemYouAre: ItemYouAre) :
    RecyclerView.Adapter<YouAreListAdapter.ViewHolder>() {
    val mContext = context
    val mItemYouAre = itemYouAre
    var isSelectedPos = -1
    val imgArray = arrayOf(
        R.drawable.ic_student, R.drawable.ic_professional, R.drawable.ic_business_man,
        R.drawable.ic_grandfather, R.drawable.ic_housewife, R.drawable.ic_teacher
    )
    val nameArray = arrayOf(
        R.string.txt_student, R.string.txt_professional, R.string.txt_business,
        R.string.txt_grandfather, R.string.txt_house_wife, R.string.txt_teacher
    )

    interface ItemYouAre {
        fun click(data: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh: ViewHolder
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_interest_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return imgArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgInterest.setImageResource(imgArray[position])
        holder.binding.txtInterest.text = mContext.resources.getString(nameArray[position])
        if (isSelectedPos == position) {
            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_bluish_gray)
            holder.binding.imgInterestTick.visibility = View.VISIBLE
        } else {
            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_white)
            holder.binding.imgInterestTick.visibility = View.GONE
        }
        holder.binding.cstInterestBox.setOnClickListener {
            mItemYouAre.click(mContext.resources.getString(nameArray[position]))
            isSelectedPos = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}