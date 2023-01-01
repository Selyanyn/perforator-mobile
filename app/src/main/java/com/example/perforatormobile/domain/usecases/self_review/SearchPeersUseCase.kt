package com.example.perforatormobile.domain.usecases.self_review

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import javax.inject.Inject

class SearchNewPeersUseCase @Inject constructor(
    private val peersRepository: PeersRepository
) {
    operator fun invoke(firstName: String) = peersRepository.searchNewPeers(firstName).toMutableList()
}