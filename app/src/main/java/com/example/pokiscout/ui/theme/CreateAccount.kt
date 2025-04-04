package com.example.pokiscout.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokiscout.PokiRoutes
import com.example.pokiscout.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val icon = if (showPassword)
        painterResource(id = R.drawable.visibilityoff)
    else
        painterResource(id = R.drawable.visibility)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA652BB))
            .padding(16.dp)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFA652BB)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Create Account", fontSize = 28.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Username*", modifier = Modifier.align(Alignment.Start))
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("USERNAME") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Text("Password*", modifier = Modifier.align(Alignment.Start))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("PASSWORD") },
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(painter = icon, contentDescription = "Toggle Password")
                        }
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Text("Confirm Password*", modifier = Modifier.align(Alignment.Start))
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("CONFIRM PASSWORD") },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (username.isNotBlank() && password == confirmPassword) {
                            navController.navigate(PokiRoutes.HomeScreen) {
                                popUpTo(PokiRoutes.LogIn) { inclusive = true }
                            }
                        } else {
                            println("Invalid input or passwords don't match")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("Create Account", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                ClickableText(
                    text = AnnotatedString("Already have an account? Log in"),
                    onClick = { navController.navigate(PokiRoutes.LogIn) },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
        }
    }
}
