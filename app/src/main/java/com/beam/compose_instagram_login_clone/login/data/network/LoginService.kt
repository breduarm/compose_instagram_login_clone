package com.beam.compose_instagram_login_clone.login.data.network

import com.beam.compose_instagram_login_clone.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun login(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginClient::class.java).login()
            response.body()?.success ?: false
        }
    }
}