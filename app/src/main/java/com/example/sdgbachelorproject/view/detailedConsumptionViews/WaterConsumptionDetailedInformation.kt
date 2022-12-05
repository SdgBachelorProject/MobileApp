package com.example.sdgbachelorproject.view.detailedConsumptionViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.WaterConsumptionDetailedInformationBinding
import kotlinx.android.synthetic.main.view_add_consumption.view.*
import kotlinx.android.synthetic.main.water_consumption_detailed_information.view.*

class WaterConsumptionDetailedInformation : Fragment() {

    private var _binding: WaterConsumptionDetailedInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WaterConsumptionDetailedInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        val picker =
            view.water_add_consumption_dish_cleaning_question.number_picker_detailed_consumption

        picker.setMinValue(0)
        picker.setMaxValue(1)
        picker.setDisplayedValues(
            arrayOf(
                "Yes, I do",
                "No, I don't"
            )
        )

        // Set width of number picker programmatically
        val pickerParams: ViewGroup.LayoutParams = picker.layoutParams
        pickerParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        picker.layoutParams = pickerParams

        return view
    }
}