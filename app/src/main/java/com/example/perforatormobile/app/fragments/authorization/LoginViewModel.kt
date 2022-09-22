package com.example.perforatormobile.app.fragments.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perforatormobile.R
import com.example.perforatormobile.domain.usecases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _userName = MutableStateFlow("")
    private val _editUserNameHelper = MutableStateFlow<Int?>(R.string.input_must_be_filled_text)
    private val _password = MutableStateFlow("")
    private val _editPasswordHelper = MutableStateFlow<Int?>(R.string.input_must_be_filled_text)

    fun onUserNameChanged(userName: String) {
        if (userName == _userName.value) return
        viewModelScope.launch {
            validateForNonEmptyField(userName, _editUserNameHelper)
            _userName.value = userName
        }
    }
    fun onPasswordChanged(password: String) {
        if (password == _password.value) return
        viewModelScope.launch {
            validateForNonEmptyField(password, _editPasswordHelper)
            _password.value = password
        }
    }

    private fun validateForNonEmptyField(field: String, helper: MutableStateFlow<Int?>) {
        helper.value = if (field.isNotEmpty()) {
            null
        } else {
            R.string.input_must_be_filled_text
        }
    }

    fun onLoginButtonClicked() {
        viewModelScope.launch {
            if (_editUserNameHelper.value == null && _editPasswordHelper.value == null) {
                loginUseCase(_userName.toString(), _password.toString())
            }
        }
    }

    fun onNotRegisteredButtonClicked() {
        //TODO: nav shenanigans
    }
}