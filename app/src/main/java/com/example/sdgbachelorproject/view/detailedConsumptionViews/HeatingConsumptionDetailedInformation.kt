package com.example.sdgbachelorproject.view.detailedConsumptionViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.HeatingConsumptionDetailedInformationBinding
import kotlinx.android.synthetic.main.heating_consumption_detailed_information.view.*
import kotlinx.android.synthetic.main.view_add_consumption.view.*


class HeatingConsumptionDetailedInformation : Fragment() {

    private var _binding: HeatingConsumptionDetailedInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HeatingConsumptionDetailedInformationBinding.inflate(inflater, container, false)
        val view = binding.root

        val picker = view.heating_add_consumption_house_type.number_picker_detailed_consumption
        val pickerTitle = view.heating_add_consumption_house_type.txt_detailed_consumption

        picker.setMinValue(0)
        picker.setMaxValue(3)
        picker.setDisplayedValues(
            arrayOf(
                "House",
                "Small apartment",
                "Mid apartment",
                "Big apartment"
            )
        )

        // Set width of number picker programmatically
        val pickerParams: ViewGroup.LayoutParams = picker.layoutParams
        pickerParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        picker.layoutParams = pickerParams

        val titleParams: ViewGroup.LayoutParams = pickerTitle.layoutParams
        titleParams.width = 650
        pickerTitle.layoutParams = titleParams

        return view
    }

}