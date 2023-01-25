package com.example.perforatormobile.domain.usecases.verify_peers

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import javax.inject.Inject

class SavePeersOfACurrentUser @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke(id: Int, ids: List<Int>) {
        peersRepository.savePeersOfACurrentUser(id, ids)
        peersRepository.approveSubordinate(id)
    }
}