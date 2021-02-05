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
import java.util.ArrayList

class ChallengeTypeListAdapter(context: Context) :
    RecyclerView.Adapter<ChallengeTypeListAdapter.ViewHolder>() {
val mContext = context

    val imgArray = arrayOf(R.drawable.ic_apple,R.drawable.ic_little,R.drawable.ic_moderate)
    val nameArray = arrayOf("SEDENTARY","LITTLE","MODERATE")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val vh: ViewHolder
                val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_challenge_row, parent, false)
        vh = ViewHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return imgArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgInterest.setImageResource(imgArray[position])
        holder.binding.txtInterest.text =  nameArray[position]
        holder.binding.cstInterestBox.setOnClickListener {
            holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_bluish_gray)
        holder.binding.imgInterestTick.visibility=View.VISIBLE
        }
    }
    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}