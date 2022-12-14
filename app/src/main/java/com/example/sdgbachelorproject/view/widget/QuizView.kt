package com.example.sdgbachelorproject.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.sdgbachelorproject.R
import kotlinx.android.synthetic.main.view_quiz_question.view.*

class QuizView : ConstraintLayout {
    constructor(context: Context) : super(context) {
        LayoutInflater.from(context).inflate(R.layout.view_quiz_question, this)
    }

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defstyle: Int) : super(
        context,
        attrs,
        defstyle
    ) {
        LayoutInflater.from(context).inflate(R.layout.view_quiz_question, this)
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.QuizView)
        val quizQuestion = arr.getText(R.styleable.QuizView_quizQuestion1)
        val quizAnswer1 = arr.getDrawable(R.styleable.QuizView_quizAnswer1)
        val quizAnswer2 = arr.getDrawable(R.styleable.QuizView_quizAnswer2)
        val quizAnswer3 = arr.getDrawable(R.styleable.QuizView_quizAnswer3)
        val quizAnswer4 = arr.getDrawable(R.styleable.QuizView_quizAnswer4)

        quiz_question.text = quizQuestion
        btn_quiz_answer1.text = quizAnswer1.toString()
        btn_quiz_answer2.text = quizAnswer2.toString()
        btn_quiz_answer3.text = quizAnswer3.toString()
        btn_quiz_answer4.text = quizAnswer4.toString()
    }
}