/*
 * Created by Jithin kozhiyodan on 28/12/20 6:51 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 28/12/20 6:51 PM
 *
 */

package com.hav.havlife.view.test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.hav.havlife.R
import com.hav.havlife.view.custom.OnScaleUpdate
import kotlinx.android.synthetic.main.activity_test.*


class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val scale = findViewById<View>(R.id.scale) as Scale
        val tvResult = findViewById<View>(R.id.tv_result) as TextView
        scale.setStartCm(21)
        scale.setPxPerMM(20)
        scale.setScaleUpdate(object : OnScaleUpdate {
            override fun onScaleSelected(result: Float) {
                tvResult.text = "$result cm"
            }
        })
        scroll.viewTreeObserver.addOnScrollChangedListener(OnScrollChangedListener {
            val scrollY: Int = scroll.getScrollY() // For ScrollView
            val scrollX: Int = scroll.getScrollX() // For HorizontalScrollView
            // DO SOMETHING WITH THE SCROLL COORDINATES
            Log.i("scrollY","$scrollY")
        })
    }
}