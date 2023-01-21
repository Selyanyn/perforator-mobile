package com.example.perforatormobile.app.fragments.self_review

import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.perforatormobile.databinding.ItemSelfReviewQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradesListViewHolder (
    private val binding: ItemSelfReviewQuestionBinding,
    private val updateGradeComment: (Int, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var data: Grade

    fun bind(item: Grade) = with(binding) {
        data = item
        selfReviewQuestionBlockTitle.text = item.category?.name
        selfReviewQuestionBlockDescription.text = item.category?.description
        selfReviewQuestionInputText.setText(item.comment)

        selfReviewQuestionInputText.doOnTextChanged {
                text, _, _, _ ->
            updateGradeComment(adapterPosition, text.toString())
        }
    }
}