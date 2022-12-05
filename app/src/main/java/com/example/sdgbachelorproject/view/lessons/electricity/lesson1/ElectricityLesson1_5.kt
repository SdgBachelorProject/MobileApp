package com.example.sdgbachelorproject.view.lessons.electricity.lesson1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.utils.switchFragment

class ElectricityLesson1_5 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_electricity_lesson1_5, container, false)
        val nextButton = view.findViewById<Button>(R.id.btn_next_lesson1_5)
        val prevButton = view.findViewById<Button>(R.id.btn_previous_lesson1_5)

        nextButton.setOnClickListener {
            switchFragment(R.id.tutorialFragmentBottom)
        }
        prevButton.setOnClickListener {
            switchFragment(R.id.electricityLesson1_4)
        }
        return view
    }

}