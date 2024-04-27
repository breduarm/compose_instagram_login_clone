package com.beam.compose_instagram_login_clone.login

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    fun onLoginChange(email: String) {
        _email.value = email
    }
}