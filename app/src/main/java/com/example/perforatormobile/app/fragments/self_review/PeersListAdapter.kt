package com.example.perforatormobile.app.fragments.self_review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.perforatormobile.databinding.ItemPeerBinding
import com.example.perforatormobile.domain.entities.Person

class PeersListAdapter(
    private val deletePeerFromList: (Int) -> Unit
): ListAdapter<Person, PeersListViewHolder>(PersonDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeersListViewHolder {
        return LayoutInflater.from(parent.context).let { inflater ->
            PeersListViewHolder(
                ItemPeerBinding.inflate(inflater, parent, false),
                ::deleteItemFromList
            )
        }
    }

    override fun onBindViewHolder(holder: PeersListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun deleteItemFromList(position: Int) {
        deletePeerFromList(position)
        notifyItemRemoved(position)
    }

}