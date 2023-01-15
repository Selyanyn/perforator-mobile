package com.example.perforatormobile.domain.usecases.authorization

import android.net.Uri
import androidx.core.net.toFile
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(userName: String, password: String, phone: String, filePath: String) {
        val file = File(filePath)
        val body = RequestBody.create(
                MediaType.parse(file.extension),
                file
        )
        userRepository.registerUser(
            userName,
            password,
            phone,
            MultipartBody.Part.createFormData("photo", "photo", body),
            ""
        )
    }
}