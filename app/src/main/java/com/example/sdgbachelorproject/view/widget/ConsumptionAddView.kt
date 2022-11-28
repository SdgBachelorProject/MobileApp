package com.example.sdgbachelorproject.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sdgbachelorproject.R
import kotlinx.android.synthetic.main.view_add_consumption.view.*

class ConsumptionAddView : ConstraintLayout {

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_add_consumption, this)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defstyle: Int) : super(
        context,
        attrs,
        defstyle
    ) {
        LayoutInflater.from(context).inflate(R.layout.view_add_consumption, this)
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.ConsumptionAddView)
        val detailedConsumptionText =
            arr.getText(R.styleable.ConsumptionAddView_detailedConsumptionText)
        val detailedConsumptionDatePickerDefault =
            arr.getInt(R.styleable.ConsumptionAddView_detailedConsumptionNumberPickerDefault, 0)
        val detailedConsumptionDatePickerMin =
            arr.getInt(R.styleable.ConsumptionAddView_detailedConsumptionNumberPickerMin, 0)
        val detailedConsumptionDatePickerMax =
            arr.getInt(R.styleable.ConsumptionAddView_detailedConsumptionNumberPickerMax, 0)

        txt_detailed_consumption.text = detailedConsumptionText
        number_picker_detailed_consumption.value = detailedConsumptionDatePickerDefault
        number_picker_detailed_consumption.minValue = detailedConsumptionDatePickerMin
        number_picker_detailed_consumption.maxValue = detailedConsumptionDatePickerMax
        setupListeners()
    }

    private fun setupListeners() {

    }
}