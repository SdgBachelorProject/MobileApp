package com.example.sdgbachelorproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.databinding.FragmentTutorialBinding
import com.example.sdgbachelorproject.utils.switchFragment
import kotlinx.android.synthetic.main.fragment_electricity_lesson1.*
import kotlinx.android.synthetic.main.fragment_tutorial.*

class TutorialFragment : Fragment() {

    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        path1_lesson1.setOnClickListener {
            switchFragment(R.id.electricityLesson1_1)
        }

        path1_lesson2.setOnClickListener {
            switchFragment(R.id.electricityLesson2_1)
        }

        path1_lesson3.setOnClickListener {
            switchFragment(R.id.electricityLesson3_1)
        }

        path1_lesson4.setOnClickListener {
            switchFragment(R.id.electricityLesson4_1)
        }

        path2_lesson1.setOnClickListener {
            switchFragment(R.id.heatingLesson1_1)
        }
    }
}