package com.example.perforatormobile.domain.usecases.authorization

import android.net.Uri
import androidx.core.net.toFile
import com.example.perforatormobile.domain.repository_interfaces.UsersRepository
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.InputStream
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UsersRepository
) {
    suspend operator fun invoke(userName: String, password: String, phone: String, inputStream: InputStream, ext: String) {
        val body = RequestBody.create(
            MediaType.parse("image/*"),
            inputStream.readBytes()
        )
        userRepository.registerUser(
            convertStringToBody(userName),
            convertStringToBody(password),
            convertStringToBody(phone),
            MultipartBody.Part.createFormData("photo", "photo.jpg", body),
            convertStringToBody("https://stackoverflow.com/")
        )
    }

    private fun convertStringToBody(str: String): RequestBody
    {
        return RequestBody.create(MediaType.parse("text/plain"), str)
    }
}