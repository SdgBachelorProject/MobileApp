package com.example.sdgbachelorproject.view.lessons.electricity.lesson2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.utils.switchFragment

class ElectricityLesson2_4 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_electricity_lesson2_4, container, false)
        val nextButton = view.findViewById<Button>(R.id.btn_next_lesson2_4)
        val prevButton = view.findViewById<Button>(R.id.btn_previous_lesson2_4)

        nextButton.setOnClickListener {
            switchFragment(R.id.electricityLesson2_5)
        }
        prevButton.setOnClickListener {
            switchFragment(R.id.electricityLesson2_3)
        }
        return view
    }
}