package com.example.perforatormobile.app.fragments.grade_questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.perforatormobile.app.fragments.self_review.GradeDiffCallback
import com.example.perforatormobile.databinding.ItemGradedQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradedQuestionsListAdapter(
    val updateCommentInViewModel: (Int, String) -> Unit,
    val updateGradeInViewModel: (Int, Int) -> Unit
)
    : ListAdapter<Grade, GradedQuestionsListViewHolder>(GradeDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradedQuestionsListViewHolder =
        LayoutInflater.from(parent.context).let { inflater ->
            GradedQuestionsListViewHolder(
                ItemGradedQuestionBinding.inflate(inflater, parent, false),
                updateCommentInViewModel,
                updateGradeInViewModel
            )
        }


    override fun onBindViewHolder(holder: GradedQuestionsListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}