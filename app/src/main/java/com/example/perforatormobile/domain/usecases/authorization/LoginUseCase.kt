package com.example.perforatormobile.domain.usecases.authorization

import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import com.example.perforatormobile.domain.server_entities.LoginRequestData
import com.example.perforatormobile.domain.server_entities.LoginUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(userName: String, password: String) =
        userRepository.loginUser(LoginRequestData(LoginUser(userName, password)))
}