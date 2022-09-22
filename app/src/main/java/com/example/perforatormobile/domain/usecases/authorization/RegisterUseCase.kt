package com.example.perforatormobile.domain.usecases.authorization

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(userName: String, password: String, phone: String) =
        userRepository.registerUser(userName, password, phone)
}