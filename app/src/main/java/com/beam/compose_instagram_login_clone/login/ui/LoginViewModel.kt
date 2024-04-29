package com.beam.compose_instagram_login_clone.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beam.compose_instagram_login_clone.login.LoginScreenContract
import com.beam.compose_instagram_login_clone.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _screenUiState = MutableStateFlow(LoginScreenUiState())
    val screenUiState = _screenUiState.asStateFlow()

    fun onLoginChanged(email: String, password: String) {
        _screenUiState.value = _screenUiState.value.copy(
            email = email,
            password = password,
            loginEnable = enableLogin(email, password)
        )
    }

    fun onClickLogin() {
        viewModelScope.launch {
            _screenUiState.value = _screenUiState.value.copy(loading = true)
            val email = _screenUiState.value.email
            val password = _screenUiState.value.password
            val result = loginUseCase(email, password)
            if (result) {
                Thread.sleep(1000)
                // TODO Navigate to next screen
                Log.i(LoginViewModel::class.java.simpleName, "Login succeed")
            }
            _screenUiState.value = _screenUiState.value.copy(loading = false)
        }
    }

    private fun enableLogin(email: String, password: String): Boolean {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 6
        return isEmailValid && isPasswordValid
    }

    data class LoginScreenUiState(
        val loading: Boolean = false,
        val contract: LoginScreenContract? = null, // TODO bind contract
        val email: String = "",
        val password: String = "",
        val loginEnable: Boolean = false,
        val onLoginChanged: (String, String) -> Unit = { _, _ -> },
        val onClickLogin: () -> Unit = {},
    )
}