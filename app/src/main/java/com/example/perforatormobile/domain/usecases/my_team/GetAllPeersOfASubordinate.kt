package com.example.perforatormobile.domain.usecases.my_team

import com.example.perforatormobile.domain.repository_interfaces.PeersRepository
import javax.inject.Inject

class GetAllPeersOfASubordinate @Inject constructor(
    private val peersRepository: PeersRepository
) {
    suspend operator fun invoke(id: Int) = peersRepository.getAllPeersOfASubordinate(id)
}