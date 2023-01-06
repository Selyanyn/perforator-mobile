package com.example.perforatormobile.app.fragments.verify_chosen_peers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.example.perforatormobile.R
import com.example.perforatormobile.app.fragments.choose_new_peers.PersonDiffCallback
import com.example.perforatormobile.databinding.ItemPeerBinding
import com.example.perforatormobile.databinding.ItemPeerToVerifyBinding
import com.example.perforatormobile.domain.entities.Person

class VerifiedPeersListAdapter(
    private val navigateToPeersChoosingMenu: (Int) -> Unit
): ListAdapter<Person, VerifiedPeersListViewHolder>(PersonDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerifiedPeersListViewHolder {
        return LayoutInflater.from(parent.context).let { inflater ->
            VerifiedPeersListViewHolder(
                ItemPeerToVerifyBinding.inflate(inflater, parent, false),
                navigateToPeersChoosingMenu
            )
        }
    }

    override fun onBindViewHolder(holder: VerifiedPeersListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun navigateTo(navController: NavController, action: Int, position: Int): Unit
    {
        val arg = Bundle().apply {
            putInt(VerifyChosenPeersFragment.SUBORDINATE_ID, currentList[position].userId)
        }
       navController.navigate(action, arg)
    }
}