package com.example.sdgbachelorproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sdgbachelorproject.R
import com.example.sdgbachelorproject.databinding.FragmentTutorialBinding
import com.example.sdgbachelorproject.utils.observeAsLiveData
import com.example.sdgbachelorproject.utils.switchFragment
import com.example.sdgbachelorproject.viewModel.QuizViewModel
import kotlinx.android.synthetic.main.fragment_electricity_lesson1.*
import kotlinx.android.synthetic.main.fragment_tutorial.*
import javax.inject.Inject

class TutorialFragment : Fragment() {

    @Inject
    lateinit var quizViewModel: QuizViewModel

    private var _binding: FragmentTutorialBinding? = null
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
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getQuizes()
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getQuizes() {
        quizViewModel.getAllQuizQuestions()
        quizViewModel.getAllQuizAnswers()
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

        path1_quiz.setOnClickListener {
            switchFragment(R.id.path1Quiz)
        }

        quizViewModel.allQuizQuestions.observeAsLiveData(viewLifecycleOwner) {
            //it.forEach {
                //println("ccc ${it.toString()}")
            //}
        }

        quizViewModel.allQuizQuestions.observeAsLiveData(viewLifecycleOwner) {
            val all = it
            val filtered = all.filter {
                it.quiz == 1
            }
            println("ccc filtered: ${filtered.size}")
        }

    }
}