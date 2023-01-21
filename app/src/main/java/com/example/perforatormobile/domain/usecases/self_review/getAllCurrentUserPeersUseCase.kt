package com.example.perforatormobile.domain.usecases.self_review

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import javax.inject.Inject

class getAllCurrentUserPeersUseCase @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke() = peersRepository.getAllCurrentUserPeers()
}