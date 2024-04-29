package com.beam.compose_instagram_login_clone.login.domain

import com.beam.compose_instagram_login_clone.login.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(user: String, password: String): Boolean =
        repository.login(user, password)
}