package com.hav.havlife.view.adapter.challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hav.havlife.R

class DailyDetailRowAdapter(context: Context) :
        RecyclerView.Adapter<DailyDetailRowAdapter.EarningHolder>() {
    val mContext = context
    //var mNearByHospital: ArrayList<NearByHospital>? = ArrayList()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EarningHolder {

//        val contentBinding: NearByHospitalFragmentBinding =
//                DataBindingUtil.inflate(
//                        LayoutInflater.from(p0.context),
//                        R.layout.near_by_hospital_fragment, p0, false
//                )
//
//
        val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(p0.getContext())
            .inflate(R.layout.content_challenge_details_row, p0, false)

        vh = EarningHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(p0: EarningHolder, p1: Int) {
//        val nearByHospital = mNearByHospital!![p1]
//        p0.binding.nearByHospital = nearByHospital
//        p0.binding.cstNearByHospitalRow.setOnClickListener {
//            MedicureCommonUtil.fireBaseEvent("hospital_selected_nearby", "")
//            val intent = Intent(mContext, NewHospitalDetailsScreen::class.java)
//            intent.putExtra("hospitalName", nearByHospital!!.hospitalName)
//            intent.putExtra("address", nearByHospital!!.address)
//            intent.putExtra("image", nearByHospital!!.hospitalPicUrl)
//            intent.putExtra("id", nearByHospital!!.hospitalId.toString())
//            mContext.startActivity(intent)
//        }
    }

//    fun setHospital(nearByHospital: ArrayList<NearByHospital>) {
//        mNearByHospital = nearByHospital
//        notifyDataSetChanged()
//    }

    class EarningHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = view
    }
}