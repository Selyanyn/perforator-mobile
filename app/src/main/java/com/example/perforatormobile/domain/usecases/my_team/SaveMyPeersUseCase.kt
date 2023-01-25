package com.example.perforatormobile.domain.usecases.my_team

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import javax.inject.Inject

class SaveMyPeersUseCase @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke(peersIds: List<Int>) = peersRepository.savePeers(peersIds)
}