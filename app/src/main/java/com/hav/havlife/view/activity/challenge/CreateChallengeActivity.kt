/*
 * Created by Jithin kozhiyodan on 14/1/21 3:03 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 14/1/21 3:03 PM
 *
 */

package com.hav.havlife.view.activity.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.hav.havlife.R
import com.hav.havlife.view.adapter.FoodYouLikeListAdapter
import com.hav.havlife.view.adapter.challenge.AddFriendsListAdapter
import com.hav.havlife.view.adapter.challenge.ChallengeTypeListAdapter
import com.hav.havlife.view.adapter.dashboard.EarningRowAdapter
import kotlinx.android.synthetic.main.activity_create_challenge.*
import kotlinx.android.synthetic.main.fragment_profile_completion_step_nine.view.*

class CreateChallengeActivity : AppCompatActivity() {
    private lateinit var challengeTypeListAdapter: ChallengeTypeListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_challenge)
        setAdapter()
    }

    private fun setAdapter() {

        var linearLayoutManagerEarning = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rcvCreateChallenge!!.layoutManager = linearLayoutManagerEarning
        rcvCreateChallenge!!.itemAnimator = DefaultItemAnimator()
        challengeTypeListAdapter = ChallengeTypeListAdapter(this)
        rcvCreateChallenge!!.adapter = challengeTypeListAdapter

        var linearLayoutManagerMind = LinearLayoutManager(this)
        rcvAddFriends!!.layoutManager = linearLayoutManagerMind
        rcvAddFriends!!.itemAnimator = DefaultItemAnimator()
        val addFriendsListAdapter =
            AddFriendsListAdapter(this)
        rcvAddFriends!!.adapter = addFriendsListAdapter
    }

}