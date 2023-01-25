package com.example.perforatormobile.app.fragments.verify_chosen_peers

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perforatormobile.databinding.ItemPeerBinding
import com.example.perforatormobile.databinding.ItemPeerToVerifyBinding
import com.example.perforatormobile.di.RetrofitModule
import com.example.perforatormobile.domain.entities.Person

class VerifiedPeersListViewHolder (
    private val binding: ItemPeerToVerifyBinding,
    private val navigateToPeersChoosingMenu: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var data: Person

    fun bind(person: Person) {
        with(binding) {
            data = person
            peerTextView.text = person.username
            val glide = Glide.with(itemView.context)
            glide.clear(peerImage)
            val url = RetrofitModule.providesBaseUrl() + person.photoUrl.trimStart('/')
            glide.load(url).into(peerImage)
            root.setOnClickListener {
                navigateToPeersChoosingMenu(data.userId)
            }
        }
    }

}