package com.example.sdgbachelorproject.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.FragmentNutritionBinding
import kotlinx.android.synthetic.main.fragment_nutrition.view.*

class NutritionFragment : Fragment() {

    private var _binding: FragmentNutritionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        val view = binding.root

        view.btn_camera.setOnClickListener {
            val intent = Intent(this.requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        return view
    }

}