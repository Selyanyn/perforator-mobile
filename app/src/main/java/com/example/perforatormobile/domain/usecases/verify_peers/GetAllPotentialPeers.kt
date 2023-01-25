package com.example.perforatormobile.domain.usecases.verify_peers

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import javax.inject.Inject

class GetAllPotentialPeers @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke() = peersRepository.getAllPotentialPeers()
}