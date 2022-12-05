package com.example.sdgbachelorproject.view.lessons.heating.lesson1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.utils.switchFragment

class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_heating_lesson1, container, false)
        val nextButton = view.findViewById<Button>(R.id.btn_heating_next_lesson1_1)

        nextButton.setOnClickListener {
            switchFragment(R.id.heatingLesson1_2)
        }

        return view
    }
}