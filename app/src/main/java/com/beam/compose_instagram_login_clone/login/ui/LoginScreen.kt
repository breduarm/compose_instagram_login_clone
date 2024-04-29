package com.beam.compose_instagram_login_clone.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beam.compose_instagram_login_clone.R
import com.beam.compose_instagram_login_clone.login.LoginScreenContract
import com.beam.compose_instagram_login_clone.login.ui.LoginViewModel.LoginScreenUiState
import com.beam.compose_instagram_login_clone.ui.theme.Compose_instagram_login_cloneTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel, contract: LoginScreenContract? = null) {
    val screenUiState: LoginScreenUiState by viewModel.screenUiState.collectAsState()

    LoginScreenContent(
        loading = screenUiState.loading,
        contract = contract,
        email = screenUiState.email,
        password = screenUiState.password,
        loginEnable = screenUiState.loginEnable,
        onLoginChanged = viewModel::onLoginChanged,
        onClickLogin = viewModel::onClickLogin,
    )
}

@Composable
fun LoginScreenContent(
    loading: Boolean,
    contract: LoginScreenContract?,
    email: String,
    password: String,
    loginEnable: Boolean,
    onLoginChanged: (String, String) -> Unit,
    onClickLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Header(contract, Modifier.align(Alignment.TopEnd))
            Body(
                email = email,
                password = password,
                loginEnable = loginEnable,
                onLoginChanged = onLoginChanged,
                onClickLogin = onClickLogin,
                modifier = Modifier.align(Alignment.Center)
            )
            Footer(Modifier.align(Alignment.BottomCenter))
        }
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

@Composable
fun Body(
    modifier: Modifier,
    email: String,
    password: String,
    loginEnable: Boolean,
    onLoginChanged: (String, String) -> Unit,
    onClickLogin: () -> Unit,
) {

    Column(modifier = modifier) {
        Logo()
        Spacer(modifier = Modifier.size(16.dp))
        Email(email) {
            onLoginChanged(it, password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            onLoginChanged(email, it)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(loginEnable, onClickLogin)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Email(email: String, onTextChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = onTextChange,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Phone number, username or email", color = Color(0xFFB5B5B5))
        }
    )
}

@Composable
fun Password(password: String, onTextChange: (String) -> Unit) {
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = onTextChange,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedTextColor = Color(0xFFB2B2B2),
            unfocusedTextColor = Color(0xFFB2B2B2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Password", color = Color(0xFFB5B5B5))
        },
        trailingIcon = {
            val icon = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = icon, contentDescription = "password visibility")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    TextButton(onClick = { /*TODO*/ }, modifier = modifier) {
        Text(
            text = "Forgot password",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun LoginButton(loginEnable: Boolean, onClickLogin: () -> Unit) {
    Button(
        onClick = onClickLogin,
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log in")
    }
}

@Composable
fun LoginDivider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 20.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Facebook logo",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as Bryan",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(24.dp))
        SignUp()
        Spacer(modifier = Modifier.size(24.dp))
    }
}

@Composable
fun SignUp() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = "Don't have an account?",
            fontSize = 12.sp,
            color = Color(0xFFB5B5B5)
        )
        Text(
            text = "Sign Up.",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

val sampleData = listOf(
    LoginScreenUiState(
        loading = false,
        contract = null,
        email = "",
        password = "",
        loginEnable = false,
        onLoginChanged = { _, _ -> },
        onClickLogin = {},
    ),
    LoginScreenUiState(
        loading = false,
        contract = null,
        email = "bryan@arm.com",
        password = "123456",
        loginEnable = true,
        onLoginChanged = { _, _ -> },
        onClickLogin = {},
    ),
    LoginScreenUiState(
        loading = true,
        contract = null,
        email = "",
        password = "",
        loginEnable = false,
        onLoginChanged = { _, _ -> },
        onClickLogin = {},
    ),
)

class LoginScreenPreviewProvider : PreviewParameterProvider<LoginScreenUiState> {
    override val values: Sequence<LoginScreenUiState> = sampleData.asSequence()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(
    @PreviewParameter(LoginScreenPreviewProvider::class) uiState: LoginScreenUiState
) {
    Compose_instagram_login_cloneTheme {
        LoginScreenContent(
            loading = uiState.loading,
            contract = uiState.contract,
            email = uiState.email,
            password = uiState.password,
            loginEnable = uiState.loginEnable,
            onLoginChanged = uiState.onLoginChanged,
            onClickLogin = uiState.onClickLogin,
        )
    }
}