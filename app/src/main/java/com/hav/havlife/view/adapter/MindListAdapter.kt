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
import com.hav.havlife.view.fragment.ProfileCompletionStepEight
import kotlinx.android.synthetic.main.content_interest_row.view.*

class MindListAdapter(context: Context, itemMind: ItemMind) :
    RecyclerView.Adapter<MindListAdapter.ViewHolder>() {
    val mContext = context
    var isSelectedPos = -1
    var mItemMind = itemMind
    val imgArray = arrayOf(
        R.drawable.ic_anxious, R.drawable.ic_discomfort, R.drawable.ic_bad,
        R.drawable.ic_cool, R.drawable.ic_going_well, R.drawable.ic_perfect
    )
    val nameArray = arrayOf(
        "ANXIOUS", "DISCOMFORT", "BAD",
        "COOL", "GOING WELL", "PERFECT"
    )

    interface ItemMind {
        fun clickMind(data: String)
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
        holder.binding.txtInterest.text = nameArray[position]
        if (isSelectedPos == position) {
            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_bluish_gray)
            holder.binding.imgInterestTick.visibility = View.VISIBLE
        } else {
            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_white)
            holder.binding.imgInterestTick.visibility = View.GONE
        }
        holder.binding.cstInterestBox.setOnClickListener {
//            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_bluish_gray)
//            holder.binding.imgInterestTick.visibility = View.VISIBLE
            mItemMind.clickMind(nameArray[position])
            isSelectedPos = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}