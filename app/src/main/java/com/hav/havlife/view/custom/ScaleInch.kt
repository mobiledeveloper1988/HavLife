/*
 * Created by Jithin kozhiyodan on 1/12/20 9:44 PM
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 1/12/20 9:44 PM
 *
 */

package com.hav.havlife.view.custom


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.hav.havlife.R
import java.text.DecimalFormat


class ScaleInch : View {
    private var pxPerMM = 20
    var width1 = 0
    var height2 = 0
    var startingPoint = 0f

    private var rulerPaint: Paint? = null

    private var largePaint: Paint? = null
    private var mediumPaint: Paint? = null
    private var smallPaint: Paint? = null


    private var textPaint: Paint? = null
    private var endPoint = 0
    private var scaleLineSmall = 0
    private var scaleLineMedium = 0
    private var scaleLineLarge = 0
    private var textStartPoint = 0

    private var rect: Rect? = null
    private var scaleUpdate: OnScaleUpdate? = null

    private var startCm = 0
    private var maxCm = 12
    private var unit = " ft"

    constructor(context: Context) : super(context) {
        if (!isInEditMode) {
            initUI()
        }
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        if (!isInEditMode) {
            initUI()
        }
    }
//    fun Scale(context: Context?, foo: AttributeSet?) {
//        super(context, foo)
//        if (!isInEditMode) {
//            initUI()
//        }
//    }
    //constructor(context: Context, attrs: AttributeSet?): this(context, attrs){}


    private fun initUI() {
        rect = Rect()
        rulerPaint = Paint()
        rulerPaint!!.style = Paint.Style.STROKE
        rulerPaint!!.strokeWidth = 0f
        rulerPaint!!.isAntiAlias = false
        rulerPaint!!.color = Color.WHITE

        largePaint = Paint()
        largePaint!!.style = Paint.Style.STROKE
        largePaint!!.strokeWidth = 0f
        largePaint!!.isAntiAlias = false
        largePaint!!.color = Color.RED

        mediumPaint = Paint()
        mediumPaint!!.style = Paint.Style.STROKE
        mediumPaint!!.strokeWidth = 0f
        mediumPaint!!.isAntiAlias = false
        mediumPaint!!.color = Color.RED

        smallPaint = Paint()
        smallPaint!!.style = Paint.Style.STROKE
        smallPaint!!.strokeWidth = 0f
        smallPaint!!.isAntiAlias = false
        smallPaint!!.color = Color.GRAY

        textPaint = TextPaint()
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.strokeWidth = 0f
        textPaint!!.isAntiAlias = true
        textPaint!!.textSize = resources.getDimension(R.dimen.txt_size)
        textPaint!!.color = Color.BLACK
        scaleLineSmall = resources.getDimension(R.dimen.scale_line_small).toInt()
        scaleLineMedium = resources.getDimension(R.dimen.scale_line_medium).toInt()
        scaleLineLarge = resources.getDimension(R.dimen.scale_line_large).toInt()
        textStartPoint = resources.getDimension(R.dimen.text_start_point).toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, ((maxCm - startCm) * 10 * pxPerMM).toInt());
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width1 = w
        height2 = h
        endPoint = width1
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        startingPoint = 0F
        for (i in startCm * 10 + 1 until maxCm * 10) {
            startingPoint += pxPerMM
            val size =
                if (i % 10 == 0) scaleLineLarge else if (i % 5 == 0) scaleLineMedium else scaleLineSmall

            if (i % 10 == 0) {
                scaleLineLarge
                canvas!!.drawLine(
                    (endPoint - size).toFloat(), startingPoint,
                    endPoint.toFloat(), startingPoint, largePaint!!
                )
            } else if (i % 5 == 0) {
                scaleLineMedium
                canvas!!.drawLine(
                    (endPoint - size).toFloat(), startingPoint,
                    endPoint.toFloat(), startingPoint, mediumPaint!!
                )
            } else {
                scaleLineSmall
                canvas!!.drawLine(
                    (endPoint - size).toFloat(), startingPoint,
                    endPoint.toFloat(), startingPoint, smallPaint!!
                )
            }

            if (i % 10 == 0) {
                Log.i("text","${120/i} $unit")
                val ft= 120-i
                canvas!!.drawText(
                    "${ft/10} $unit",
                    (endPoint - textStartPoint).toFloat(), startingPoint + 8, textPaint!!
                )
            }
        }
        canvas!!.getClipBounds(rect)
        val h = ((rect!!.bottom - rect!!.top) / 2).toFloat()
        var selected = (rect!!.top + h) / (pxPerMM * 10) + startCm
        selected = DecimalFormat("#####.#").format(selected).toFloat()

        if (scaleUpdate != null) {
            scaleUpdate!!.onScaleSelected(selected)
        }
    }

    fun setScaleUpdate(l: OnScaleUpdate) {
        scaleUpdate = l
    }

    fun setMaxCm(maxCm1: Int) {
        maxCm = maxCm1
    }

    fun setStartCm(startCm1: Int) {
        startCm = startCm1
    }

    fun setUnit(unit1: String?) {
        unit = unit1!!
    }

    fun setPxPerMM(pxPerMM1: Int) {
        pxPerMM = pxPerMM1
    }
}