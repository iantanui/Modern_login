package com.modernlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.modernlogin.ui.theme.ModernLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModernLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isPasswordVisible by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val density = LocalDensity.current.density

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Lock Icon
        Icon(
            imageVector = Icons.Default.Lock, // Lock
            contentDescription = "Lock Icon",
            modifier = Modifier
                .size(150.dp)
                .padding(top = 20.dp)
                .fillMaxWidth()
                .layoutId("lockIcon")
        )

        // Text Above Input
        Text(
            text = "Welcome back !",
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .layoutId("textAboveFields"),
        )

        // Email
        TextField(
            value = email,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                //
            }),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(20.dp)
                .layoutId("emailField")
                .clip(RoundedCornerShape(10.dp)),
            placeholder = {
                Icon(
                    imageVector = Icons.Filled.MailOutline,
                    contentDescription = "Email Icon"
                )
                Text("Email", modifier = Modifier.padding(start = 30.dp))
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        // Password Input Field
        TextField(
            value = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(onDone = {
                // Handle the action when Done is clicked (e.g., perform login)
                keyboardController?.hide()
            }),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(20.dp)
                .layoutId("passwordField")
                .clip(RoundedCornerShape(10.dp)),
            placeholder = {
                Icon(
                    imageVector = Icons.Filled.MailOutline,
                    contentDescription = "Password Icon"
                )
                Text("Password", modifier = Modifier.padding(start = 30.dp))
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        // Forgot Password Link
        Text(text = "Forgot Password?",
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clickable {
                    // Handle the action when the "Forgot Password" link is clicked
                }
                .layoutId("forgotPasswordLink"))

        // Sign-In Button
        OutlinedButton(
            onClick = {
                // Handle the Sign-In button click here
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(20.dp)
                .layoutId("signInButton")
        ) {
            Text(text = "Sign In")
        }

        // Google Option
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "Or continue with",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .layoutId("textBetweenSignInAndGoogle")
            )

            Divider(
                modifier = Modifier
                    .weight(1f)
            )
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        // Option to Use Google
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.White)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .clickable {
                    //
                }
                .padding(10.dp)
                .layoutId("googleOption")
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Icon",
                modifier = Modifier
                    .size(65.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        // Sign-Up Link for New Users
        Text(
            text = "Sign Up Now",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    // Handle the action when the "Sign Up" link is clicked
                }
                .padding(20.dp)
                .layoutId("signUpLink")
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    ModernLoginTheme {
        LoginScreen()
    }
}