package com.example.perforatormobile.app.fragments.self_review

import androidx.recyclerview.widget.RecyclerView
import com.example.perforatormobile.databinding.ItemSelfReviewQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradesListViewHolder (
    private val binding: ItemSelfReviewQuestionBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var data: Grade

    fun bind(item: Grade) = with(binding) {
        data = item
    }
}