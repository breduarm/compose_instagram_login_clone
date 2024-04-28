package com.beam.compose_instagram_login_clone.login.data.network

import com.beam.compose_instagram_login_clone.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/50eb8342-30fa-410c-9252-f69d7c15995e")
    suspend fun login(): Response<LoginResponse>
}