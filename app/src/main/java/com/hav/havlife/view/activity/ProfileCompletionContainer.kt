/*
 * Created by Jithin kozhiyodan on 29/11/20 5:33 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 29/11/20 5:33 PM
 *
 */

package com.hav.havlife.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.hav.havlife.BaseActivity
import com.hav.havlife.R
import com.hav.havlife.model.request_data.user.ProfileRequest
import com.hav.havlife.view.fragment.ProfileCompletionFinish
import com.hav.havlife.view.fragment.ProfileCompletionStepFive
import com.hav.havlife.view.fragment.ProfileCompletionStepOne
import kotlinx.android.synthetic.main.activity_profile_completion_container.*

class ProfileCompletionContainer : BaseActivity() {
    var fragmentTransaction: FragmentTransaction? = null
    var profileRequest = ProfileRequest()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_completion_container)
        setSupportActionBar(topAppBarProfileContainer)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        topAppBarProfileContainer.setNavigationOnClickListener { onBackPressed() }
        supportActionBar!!.title = " "
        val fragment = ProfileCompletionStepOne()
        changeFragment(fragment, "ProfileCompletionStepOne")

    }

    fun hideToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            val myFragment: Fragment? =
                supportFragmentManager.findFragmentByTag("ProfileCompletionFinish")
            if (myFragment != null && myFragment.isVisible) {
//                supportActionBar!!.setDisplayHomeAsUpEnabled(false)
//                supportActionBar!!.setDisplayShowHomeEnabled(false)
            } else {
                super.onBackPressed()
            }

        }
    }

    fun changeFragment(fragment: Fragment, title: String) {
        val popFragment = supportFragmentManager.findFragmentByTag(title)
        val bundle = Bundle()
        //profileRequest.email = "jithinramdas@gmail.com"
        bundle.putSerializable("obj", profileRequest)

        fragment.arguments = bundle
        if (popFragment != null) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack(
                    title,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                );
                fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction!!.add(R.id.container, fragment, title).addToBackStack(title)
                fragmentTransaction!!.commit()
            }
        } else {
            //Toast.makeText(this, "popFragment null", Toast.LENGTH_SHORT).show()
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction!!.add(R.id.container, fragment, title).addToBackStack(title)
            fragmentTransaction!!.commit()

        }
    }

}