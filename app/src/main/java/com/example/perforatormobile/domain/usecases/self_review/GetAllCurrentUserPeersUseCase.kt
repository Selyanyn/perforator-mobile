package com.example.perforatormobile.domain.usecases.self_review

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import javax.inject.Inject

class GetAllCurrentUserPeersUseCase @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke() = peersRepository.getAllCurrentUserPeers()
}