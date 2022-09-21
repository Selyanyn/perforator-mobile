package com.example.perforatormobile.domain.usecases.authorization

import com.example.perforatormobile.domain.repository_interfaces.ReviewsRepository
import com.example.perforatormobile.domain.repository_interfaces.UserRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        userRepository.loginUser(email, password)
}