package com.example.sdgbachelorproject.view.lessons.electricity.lesson1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.utils.switchFragment

class ElectricityLesson1 : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_electricity_lesson1, container, false)
        val nextButton = view.findViewById<Button>(R.id.btn_next_lesson1_1)

        nextButton.setOnClickListener {
            switchFragment(R.id.electricityLesson1_2)
        }
        return view
    }
}