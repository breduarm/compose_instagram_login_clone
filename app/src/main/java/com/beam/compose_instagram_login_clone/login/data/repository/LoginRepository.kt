package com.beam.compose_instagram_login_clone.login.data.repository

import com.beam.compose_instagram_login_clone.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val service: LoginService) {

    suspend fun login(user: String, password: String): Boolean =
        service.login(user, password)
}