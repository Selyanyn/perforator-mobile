package com.example.perforatormobile.app.fragments.grade_questions

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.perforatormobile.databinding.ItemGradedQuestionBinding
import com.example.perforatormobile.databinding.ItemSelfReviewQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradedQuestionsListViewHolder (
    private val binding: ItemGradedQuestionBinding,
    private val updateGradeComment: (Int, String) -> Unit,
    private val updateGradeGrade: (Int, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var data: Grade

    fun bind(item: Grade) = with(binding) {
        data = item
        gradedQuestionBlockTitle.text = data.category.name
        gradedQuestionBlockDescription.text = data.category.description
        gradedQuestionInputText.setText(data.comment)
        gradeQuestionRatingBar.numStars = 4
        gradeQuestionRatingBar.stepSize = 1.0f
        gradeQuestionRatingBar.rating = data.grade.toFloat()

        gradedQuestionInputText.doOnTextChanged {
                text, _, _, _ ->
            updateGradeComment(adapterPosition, text.toString())
        }

        gradeQuestionRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateGradeGrade(adapterPosition, rating.toInt())
        }
    }
}