package com.beam.compose_instagram_login_clone.login.data.repository

import com.beam.compose_instagram_login_clone.login.data.network.LoginService

class LoginRepository {

    private val api = LoginService()

    suspend fun login(user: String, password: String): Boolean =
        api.login(user, password)
}