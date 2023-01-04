package com.example.perforatormobile.domain.usecases

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val userRepository: UsersRepository) {
    operator fun invoke() = userRepository.getCurrentUser()
}