package com.example.sdgbachelorproject.view.lessons.electricity.lesson1

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.example.sdgbachelorproject.databinding.FragmentPath1QuizBinding
import com.example.sdgbachelorproject.model.data.AllQuizAnswersItem
import com.example.sdgbachelorproject.model.data.AllQuizQuestionsItem
import com.example.sdgbachelorproject.utils.observeAsLiveData
import com.example.sdgbachelorproject.view.MyApplication
import com.example.sdgbachelorproject.viewModel.QuizViewModel
import kotlinx.android.synthetic.main.fragment_path1_quiz.*
import kotlinx.android.synthetic.main.view_quiz_question.view.*
import javax.inject.Inject

class Path1Quiz : Fragment() {

    @Inject
    lateinit var quizViewModel: QuizViewModel

    private var _binding: FragmentPath1QuizBinding? = null
    private val binding get() = _binding!!

    val quiz1QuestionsList = mutableListOf<AllQuizQuestionsItem>()
    val quiz1AnswersList = mutableListOf<AllQuizAnswersItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger
        (activity?.application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPath1QuizBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {

        quizViewModel.allQuizQuestions.observeAsLiveData(viewLifecycleOwner) {
            val all = it
            val filtered = all.filter {
                it.quiz == 1
            }

            quiz1QuestionsList.addAll(filtered)
        }

        quizViewModel.allQuizAnswers.observeAsLiveData(viewLifecycleOwner) {
            val allAnswers = it
            val filteredAnswers = allAnswers.filter { it.quiz == 1 }
            quiz1AnswersList.addAll(filteredAnswers)

            setupQuizData()
        }

    }

    private fun setupListeners() {
        btn_check_answers.setOnClickListener {
            checkAnswers()
        }
    }

    private fun setupQuizData() {
        val questionViews = listOf(quiz_view_1, quiz_view_2, quiz_view_3, quiz_view_4)

        if (quiz1AnswersList.isNotEmpty() && quiz1QuestionsList.isNotEmpty()) {
            for ((index, questionView) in questionViews.withIndex()) {
                val question = quiz1QuestionsList[index]
                val answers = quiz1AnswersList.filter { it.quiz_question == index + 1 }

                if (questionView == quiz_view_3) {
                    questionView.quiz_question.text = question.content
                    questionView.btn_quiz_answer1.text = answers[0].content
                    questionView.btn_quiz_answer2.text = answers[1].content
                    quiz_view_3.btn_quiz_answer3.visibility = View.INVISIBLE
                    quiz_view_3.btn_quiz_answer4.visibility = View.INVISIBLE
                } else {
                    questionView.quiz_question.text = question.content
                    questionView.btn_quiz_answer1.text = answers[0].content
                    questionView.btn_quiz_answer2.text = answers[1].content
                    questionView.btn_quiz_answer3.text = answers[2].content
                    questionView.btn_quiz_answer4.text = answers[3].content
                }
            }
        }
    }


    private fun checkAnswers() {
        val radioGroups = listOf(
            quiz_view_1.radio_group,
            quiz_view_2.radio_group,
            quiz_view_3.radio_group,
            quiz_view_4.radio_group
        )

        // Check if answers are correct
        val answers = radioGroups.map { it.checkedRadioButtonId }
        val correctAnswers = quiz1AnswersList.filter { it.correct }.map { it.content }
        for ((index, answer) in answers.withIndex()) {
            if (answer == -1) {
                Toast.makeText(context, "Please select all answers", Toast.LENGTH_LONG).show()
                return
            }

            val question = radioGroups[index].findViewById<RadioButton>(answer)
            val correct = question.text == correctAnswers[index]
            question.background = if (correct) Color.GREEN.toDrawable() else Color.RED.toDrawable()
        }

        // Disable further answers
        radioGroups.forEach { group ->
            for (i in 0 until group.getChildCount()) {
                group.getChildAt(i).setEnabled(false)
            }
        }
    }


}