package com.example.perforatormobile.app.fragments.self_review

import androidx.recyclerview.widget.DiffUtil
import com.example.perforatormobile.domain.entities.Person

object PersonDiffCallback : DiffUtil.ItemCallback<Person>()  {
    override fun areItemsTheSame(oldItem: Person, newItem: Person) =
        oldItem.userId == newItem.userId

    override fun areContentsTheSame(oldItem: Person, newItem: Person) =
        oldItem == newItem
}