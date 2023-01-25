package com.example.perforatormobile.app.fragments.choose_new_peers

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perforatormobile.databinding.ItemPeerBinding
import com.example.perforatormobile.di.RetrofitModule
import com.example.perforatormobile.domain.entities.Person

class PeersListViewHolder (
    private val binding: ItemPeerBinding,
    private val deletePeerFromList: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var data: Person

    init {
        binding.deletePeerButton.setOnClickListener {
            deletePeerFromList(layoutPosition)
        }
    }

    fun bind(person: Person) {
        with(binding) {
            data = person
            peerTextView.text = person.username
            val glide = Glide.with(itemView.context)
            glide.clear(peerImage)
            val url = RetrofitModule.providesBaseUrl() + person.photoUrl
            glide.load(url).into(peerImage)
        }
    }

}