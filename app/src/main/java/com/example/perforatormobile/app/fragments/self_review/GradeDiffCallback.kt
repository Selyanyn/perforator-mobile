package com.example.perforatormobile.app.fragments.self_review

import androidx.recyclerview.widget.DiffUtil
import com.example.perforatormobile.domain.entities.Grade

object GradeDiffCallback : DiffUtil.ItemCallback<Grade>()  {
    override fun areItemsTheSame(oldItem: Grade, newItem: Grade) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Grade, newItem: Grade) =
        oldItem == newItem
}