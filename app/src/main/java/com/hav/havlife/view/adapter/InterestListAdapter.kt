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
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R
import com.hav.havlife.view.fragment.ProfileCompletionStepTwo
import kotlinx.android.synthetic.main.content_interest_row.view.*
import java.util.ArrayList

class InterestListAdapter(context: Context, itemInterest: ItemInterest) :
    RecyclerView.Adapter<InterestListAdapter.ViewHolder>() {
    val mContext = context
    val interest = itemInterest
    private var selectedInterest: ArrayList<String>? = ArrayList()
    val imgArray = arrayOf(
        R.drawable.ic_diet, R.drawable.ic_nutritions, R.drawable.ic_fitness,
        R.drawable.ic_cycling, R.drawable.ic_sports, R.drawable.ic_smoking,
        R.drawable.ic_running, R.drawable.ic_swimming, R.drawable.ic_yoga
    )
    val nameArray = arrayOf(
        R.string.txt_diet, R.string.txt_nutrition, R.string.txt_fitness,
        R.string.txt_cycling, R.string.txt_sport, R.string.txt_smoking,
        R.string.txt_running, R.string.txt_swimming, R.string.txt_yoga
    )

    interface ItemInterest {
        fun click(data: ArrayList<String>?)
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imgInterest.setImageResource(imgArray[position])
        holder.binding.txtInterest.text = mContext.resources.getString(nameArray[position])
        holder.binding.cstInterestBox.setOnClickListener {

            if(holder.binding.imgInterestTick.isVisible){
                selectedInterest!!.removeIf { n -> n === mContext.resources.getString(nameArray[position]) }
                holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_white)
                holder.binding.imgInterestTick.visibility = View.GONE
            }else{
                selectedInterest!!.add(mContext.resources.getString(nameArray[position]))
                holder.binding.relInterest.setBackgroundResource(R.drawable.round_bg_bluish_gray)
                holder.binding.imgInterestTick.visibility = View.VISIBLE
            }
            interest.click(selectedInterest)

        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var binding = view
    }
}