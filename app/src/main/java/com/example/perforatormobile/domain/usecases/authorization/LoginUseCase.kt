package com.example.perforatormobile.domain.usecases.authorization

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(userName: String, password: String) =
        userRepository.loginUser(userName, password)
}