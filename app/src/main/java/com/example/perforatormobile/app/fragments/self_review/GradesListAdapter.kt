package com.example.perforatormobile.app.fragments.self_review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.perforatormobile.databinding.ItemSelfReviewQuestionBinding
import com.example.perforatormobile.domain.entities.Grade

class GradesListAdapter : ListAdapter<Grade, GradesListViewHolder>(GradeDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).let { inflater ->
            GradesListViewHolder(
                ItemSelfReviewQuestionBinding.inflate(inflater, parent, false)
            )
        }

    override fun onBindViewHolder(holder: GradesListViewHolder, position: Int) =
        holder.bind(getItem(position))
}