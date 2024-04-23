package com.beam.compose_instagram_login_clone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beam.compose_instagram_login_clone.ui.theme.Compose_instagram_login_cloneTheme

@Composable
fun LoginScreen(contract: LoginScreenContract? = null) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Header(contract, Modifier.align(Alignment.TopEnd))
    }
}

@Composable
fun Header(contract: LoginScreenContract?, modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close app",
        modifier = modifier.clickable { contract?.closeApp() }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    Compose_instagram_login_cloneTheme {
        LoginScreen()
    }
}