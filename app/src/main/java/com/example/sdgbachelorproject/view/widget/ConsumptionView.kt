package com.example.sdgbachelorproject.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sdgbachelorproject.R
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.view_current_consumption.view.*

class ConsumptionView : ConstraintLayout {

    val onInfoButtonClick = PublishSubject.create<Unit>()
    val onAddConsumptionButtonClick = PublishSubject.create<Unit>()

    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_current_consumption, this)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defstyle: Int) : super(
        context,
        attrs,
        defstyle
    ) {
        LayoutInflater.from(context).inflate(R.layout.view_current_consumption, this)
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.ConsumptionView)
        val consumptionTitleText = arr.getText(R.styleable.ConsumptionView_consumptionTitleText)
        val consumptionValueText = arr.getText(R.styleable.ConsumptionView_consumptionValueText)
        val consumptionIcon = arr.getDrawable(R.styleable.ConsumptionView_consumptionIcon)

        consumption_name.text = consumptionTitleText
        consumption_value.text = consumptionValueText
        ic_consumption_icon.background = consumptionIcon
        setupListeners()
    }

    private fun setupListeners() {
        consumption_value_info.setOnClickListener {
            onInfoButtonClick.onNext(Unit)
        }

        btn_add_consumption.setOnClickListener {
            onAddConsumptionButtonClick.onNext(Unit)
        }

    }
}