package com.example.sdgbachelorproject.view.detailedConsumptionViews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.WaterConsumptionDetailedInformationBinding
import com.example.sdgbachelorproject.data.model.WaterConsumption
import com.example.sdgbachelorproject.view.MyApplication
import com.example.sdgbachelorproject.viewModel.ConsumptionsViewModel
import com.example.sdgbachelorproject.viewModel.SignInViewModel
import kotlinx.android.synthetic.main.view_add_consumption.view.*
import kotlinx.android.synthetic.main.water_consumption_detailed_information.*
import kotlinx.android.synthetic.main.water_consumption_detailed_information.view.*
import javax.inject.Inject

class WaterConsumptionDetailedInformation : Fragment() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    @Inject
    lateinit var consumptionsViewModel: ConsumptionsViewModel

    private var _binding: WaterConsumptionDetailedInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        btn_change_consumptions_water.setOnClickListener {
            val bath =
                view?.water_add_consumption_bath?.number_picker_detailed_consumption?.value
            val showerAmount =
                view?.water_add_consumption_shower_amounts?.number_picker_detailed_consumption?.value
            val showerTime =
                view?.water_add_consumption_shower_time?.number_picker_detailed_consumption?.value
            val dishesQuestion =
                view?.water_add_consumption_dish_cleaning_question?.number_picker_detailed_consumption?.value
            val dishesAmount =
                view?.water_add_consumption_dish_cleaning_amount?.number_picker_detailed_consumption?.value
            val washingQuestion =
                view?.water_add_consumption_washing_machine?.number_picker_detailed_consumption?.value

            val dishesQuestionArray = arrayOf(
                "yes",
                "no"
            )
            var dishesQuestionBoolean = false

            if (dishesQuestionArray[dishesQuestion!!] == "yes") {
                dishesQuestionBoolean = true
            }

            val userId = signInViewModel.currentUser.value?.uid

            val waterConsumption = WaterConsumption(
                bath!!,
                showerAmount!!,
                showerTime!!,
                dishesQuestionBoolean,
                dishesAmount!!,
                washingQuestion!!,
                userId
            )

            if (userId != null) {
                consumptionsViewModel.postWaterConsumptions(userId, waterConsumption)
            }
            Toast.makeText(this.context, "Consumptions updated!", Toast.LENGTH_LONG).show()
        }
    }
}