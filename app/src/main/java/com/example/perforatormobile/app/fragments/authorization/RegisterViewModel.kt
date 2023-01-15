package com.example.perforatormobile.app.fragments.authorization

import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.R
import com.example.perforatormobile.domain.usecases.authorization.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _username = MutableStateFlow("")
    private val _editUserNameHelper = MutableStateFlow<Int?>(R.string.input_must_be_filled_text)
    private val _password = MutableStateFlow("")
    private val _editPasswordHelper = MutableStateFlow<Int?>(R.string.input_must_be_filled_text)
    private val _phone = MutableStateFlow("")
    private val _editPhoneHelper = MutableStateFlow<Int?>(R.string.input_must_be_filled_text)
    val userPhotoURI = MutableStateFlow<Uri?>(null)
    val isUserSuccessfullyRegistered = MutableStateFlow(false)

    private val _showDialog = MutableStateFlow(false)
    private val _isUserRegistered = MutableStateFlow(false)

    val showDialog: StateFlow<Boolean> = _showDialog
    val isUserRegistered: StateFlow<Boolean> = _isUserRegistered

    val editUserNameHelper: StateFlow<Int?> = _editUserNameHelper
    val editPasswordHelper: StateFlow<Int?> = _editPasswordHelper
    val editPhoneHelper: StateFlow<Int?> = _editPhoneHelper

    var userPhotoFilePath = ""

    fun onUserNameChanged(userName: String) {
        if (userName == _username.value) return
        viewModelScope.launch {
            validateForNonEmptyField(userName, _editUserNameHelper)
            _username.value = userName
        }
    }

    fun onPasswordChanged(password: String) {
        if (password == _password.value) return
        viewModelScope.launch {
            validatePassword(password)
            _password.value = password
        }
    }

    fun onPhoneChanged(phone: String) {
        if (phone == _phone.value) return
        viewModelScope.launch {
            validatePhone(phone)
            _phone.value = phone
        }
    }

    private fun validateForNonEmptyField(field: String, helper: MutableStateFlow<Int?>) {
        helper.value = if (field.isNotEmpty()) {
            null
        } else {
            R.string.input_must_be_filled_text
        }
    }

    private fun validatePassword(password: String) {
        _editPasswordHelper.value = if (password.isEmpty()) {
            R.string.input_must_be_filled_text
        } else if (password.length < 8) {
            R.string.password_invalid_length
        } else if (!password.matches(".*[A-ZА-Я].*".toRegex())) {
            R.string.password_must_contain_uppercase_letters
        } else if (!password.matches(".*[a-zа-я].*".toRegex())) {
            R.string.password_must_contain_lowercase_letters
        } else if (!password.matches(".*[1-9].*".toRegex())) {
            R.string.password_must_contain_numbers
        } else
            null
    }

    private fun validatePhone(phone: String) {
        val trimmedPhone = phone.trim()
        _editPhoneHelper.value = if (trimmedPhone.isEmpty()) {
            R.string.input_must_be_filled_text
        } else if (!Patterns.PHONE.matcher(trimmedPhone).matches()) {
            R.string.incorrect_phone_format
        } else {
            null
        }

    }

    fun onRegisterButtonClicked() {
        viewModelScope.launch {
            if (_editUserNameHelper.value == null && _editPasswordHelper.value == null &&
                _editPhoneHelper.value == null && userPhotoURI.value !== null
            ) {
                registerUseCase(
                    _username.value,
                    _password.value,
                    _phone.value,
                    userPhotoFilePath
                )
            }
        }
    }

    fun onAlreadyRegisteredButtonClicked() {
        //TODO: nav shenanigans
    }
}