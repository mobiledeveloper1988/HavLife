/*
 * Created by Jithin kozhiyodan on 13/10/20 6:05 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 10/10/20 12:58 AM
 *
 */

package com.hav.havlife.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.constraintlayout.motion.widget.MotionLayout
import com.hav.havlife.BaseActivity
import com.hav.havlife.HavLifeApp.Companion.appPreference
import com.hav.havlife.R
import com.hav.havlife.view.activity.login.SignInScreen
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : BaseActivity() {
    private var handler: Handler? = null
    private val sPlashDelay: Long = 500 //3 seconds
    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            if(appPreference!!.getLogin()) {
                val intent = Intent(this, ProfileCompletionContainer::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, SignInScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val deviceID = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
        // appPreference!!.setDeviceID(deviceID)
        //Initialize the Handler for timer
        handler = Handler()

        cstMotionLayoutSplash.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                p0!!.getTransition(R.id.tranAnimateToEnd).setEnable(false)
                //startActivity(Intent(this@SplashScreen,::class.java))
                //Navigate with delay
                handler!!.postDelayed(mRunnable, sPlashDelay)
               // move()
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) { }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) { }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) { }
        })







    }
    override fun onDestroy() {
        super.onDestroy()
        if (handler != null) {
            handler!!.removeCallbacks(mRunnable)
        }
    }
}
