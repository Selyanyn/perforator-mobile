package com.example.perforatormobile.domain.usecases.verify_peers

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import com.example.perforatormobile.domain.usecases.GetCurrentUserUseCase
import javax.inject.Inject

class GetAllSubordinatesUseCase @Inject constructor(
    private val peersRepository: PeersRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    operator fun invoke() = peersRepository.getAllSubordinates(getCurrentUserUseCase().id)
}