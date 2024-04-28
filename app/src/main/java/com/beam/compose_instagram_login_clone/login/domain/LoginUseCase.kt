package com.beam.compose_instagram_login_clone.login.domain

import com.beam.compose_instagram_login_clone.login.data.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): Boolean =
        repository.login(user, password)
}