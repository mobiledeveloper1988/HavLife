package com.hav.havlife.view.adapter.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hav.havlife.view.fragment.dashboard.home.DailyActivity
import com.hav.havlife.view.fragment.dashboard.home.DailyIntake
import com.hav.havlife.view.fragment.dashboard.home.Statistics

class DashBoardActivityPageAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {//, appointment: BookingInfo
    //private val mAppointment = appointment
    override fun getItem(p0: Int): Fragment {
        var fragment: Fragment? = null
        //if (hospitalDetailsResponds != null) {

            when (p0) {
                0 -> {
                    fragment = DailyActivity()
                    val bundle = Bundle()
                    //bundle.putSerializable("upcoming",mAppointment.bookingsUpcoming as Serializable)
                    fragment!!.arguments = bundle

                }
                1 -> {
                    fragment = DailyIntake()
                    val bundle = Bundle()
                   // bundle.putSerializable("past",mAppointment.bookingsPast as Serializable)
                    fragment!!.arguments = bundle
                }
                2 -> {
                    fragment = Statistics()
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
            0 -> "Daily Activity"
            1 -> "Daily Intake"
            2 -> "Statistics"
            else -> {
                return "Gallery"
            }

        }
    }
}