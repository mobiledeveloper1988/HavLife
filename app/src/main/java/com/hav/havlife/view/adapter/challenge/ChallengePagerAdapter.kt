package com.hav.havlife.view.adapter.challenge

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hav.havlife.R
import com.hav.havlife.view.fragment.dashboard.challenge.ChallengeDetails
import com.hav.havlife.view.fragment.dashboard.challenge.ChallengeTracker


class ChallengePagerAdapter(context: Context,
                            fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val mContext=context
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = ChallengeTracker()
                val bundle = Bundle()
                Log.i("HomePagerAdapter", "i called PastFragment")
                // bundle.putSerializable("upcoming",mAppointment.bookingsUpcoming as Serializable)
                fragment!!.arguments = bundle

            }
            1 -> {
                fragment = ChallengeDetails()
                val bundle = Bundle()
                Log.i("HomePagerAdapter", "i called TodayFragment")
                //bundle.putSerializable("past",mAppointment.bookingsPast as Serializable)
                fragment!!.arguments = bundle
            }

        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.resources.getString(R.string.txt_challenge_tab_one)//"PAST"
            1 -> mContext.resources.getString(R.string.txt_challenge_tab_two)//"TODAY"
            else -> {
                return ""
            }

        }
    }
}