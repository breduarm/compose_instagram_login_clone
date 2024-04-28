package com.beam.compose_instagram_login_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.beam.compose_instagram_login_clone.login.ui.LoginScreen
import com.beam.compose_instagram_login_clone.login.LoginScreenContract
import com.beam.compose_instagram_login_clone.login.ui.LoginViewModel
import com.beam.compose_instagram_login_clone.ui.theme.Compose_instagram_login_cloneTheme

class MainActivity : ComponentActivity(), LoginScreenContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_instagram_login_cloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(viewModel = LoginViewModel(),  contract = this)
                }
            }
        }
    }

    override fun closeApp() {
        finish()
    }
}