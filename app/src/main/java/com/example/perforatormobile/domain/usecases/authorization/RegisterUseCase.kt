package com.example.perforatormobile.domain.usecases.authorization

import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import com.example.perforatormobile.domain.repository_interfaces.UserRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userName: String, password: String, phone: String) =
        userRepository.registerUser(userName, password, phone)
}