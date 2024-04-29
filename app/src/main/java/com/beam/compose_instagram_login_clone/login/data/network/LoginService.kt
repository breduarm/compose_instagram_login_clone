package com.beam.compose_instagram_login_clone.login.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun login(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = loginClient.login()
            response.body()?.success ?: false
        }
    }
}