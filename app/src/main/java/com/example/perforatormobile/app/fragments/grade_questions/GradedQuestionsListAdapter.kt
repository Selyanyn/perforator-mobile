package com.example.perforatormobile.app.fragments.grade_questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.perforatormobile.app.fragments.self_review.GradeDiffCallback
import com.example.perforatormobile.databinding.ItemGradedQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradedQuestionsListAdapter(val updateGradeInViewModel: (Int, Grade) -> Unit)
    : ListAdapter<Grade, GradedQuestionsListViewHolder>(GradeDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradedQuestionsListViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            GradedQuestionsListViewHolder(
                ItemGradedQuestionBinding.inflate(inflater, parent, false),
                ::updateCommentInAdapter,
                ::updateGradeInAdapter
            )
        }


    override fun onBindViewHolder(holder: GradedQuestionsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun updateCommentInAdapter(position: Int, text: String) {
        val elementList = currentList.toMutableList()
        elementList[position] = elementList[position].copy(comment = text);
        submitList(elementList)
        updateGradeInViewModel(position, elementList[position])
    }

    private fun updateGradeInAdapter(position: Int, value: Int) {
        val elementList = currentList.toMutableList()
        elementList[position] = elementList[position].copy(grade = value);
        submitList(elementList)
        updateGradeInViewModel(position, elementList[position])
    }
}