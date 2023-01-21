package com.example.perforatormobile.app

import androidx.lifecycle.ViewModel
import com.example.perforatormobile.di.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow

class ActivityViewModel: ViewModel() {
    private val isUserLoggedIn = MutableStateFlow(false)
    private val userId = MutableStateFlow(0)
    val sessionManager = SessionManager()

    fun getUserId(): Int
    {
        if (!isUserLoggedIn.value) {
            throw Exception("User is not logged in!")
        }
        return userId.value
    }

    fun setUserInfo(userId: Int)
    {
        isUserLoggedIn.value = true
        this.userId.value = userId
    }
}