package com.hav.havlife.view.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hav.havlife.view.fragment.dashboard.home.DailyActivity
import com.hav.havlife.view.fragment.dashboard.home.DailyIntake
import com.hav.havlife.view.fragment.dashboard.home.Statistics
import com.hav.havlife.view.fragment.referral.EnrolledFragment
import com.hav.havlife.view.fragment.referral.HistoryFragment
import com.hav.havlife.view.fragment.referral.ReferFragment

class ReferralPageAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {//, appointment: BookingInfo
    //private val mAppointment = appointment
    override fun getItem(p0: Int): Fragment {
        var fragment: Fragment? = null
        //if (hospitalDetailsResponds != null) {

            when (p0) {
                0 -> {
                    fragment = ReferFragment()
                    val bundle = Bundle()
                    //bundle.putSerializable("upcoming",mAppointment.bookingsUpcoming as Serializable)
                    fragment!!.arguments = bundle

                }
                1 -> {
                    fragment = EnrolledFragment()
                    val bundle = Bundle()
                   // bundle.putSerializable("past",mAppointment.bookingsPast as Serializable)
                    fragment!!.arguments = bundle
                }
                2 -> {
                    fragment = HistoryFragment()
                    val bundle = Bundle()
                    // bundle.putSerializable("past",mAppointment.bookingsPast as Serializable)
                    fragment!!.arguments = bundle
                }

            }
        //}

        return fragment!!
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Refer"
            1 -> "Enrolled"
            2 -> "History"
            else -> {
                return "Gallery"
            }

        }
    }
}