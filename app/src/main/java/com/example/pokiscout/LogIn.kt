package com.example.pokiscout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogIn(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if(passwordVisibility)
        painterResource(id=R.drawable.visibilityoff)
    else painterResource( id=R.drawable.visibility)

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
                Image(
                    painter = painterResource(id = R.drawable.maserballogo),
                    contentDescription = "Masterball",
                    modifier = Modifier
                        .size(150.dp)
                        .offset(y=-110.dp),
                )

                Text(text="Welcome Back",fontSize=28.sp, fontWeight = FontWeight.Bold,
                    modifier=Modifier
                        .offset(y=-100.dp)

                )
                Text(text = "Login to your account",
                    modifier=Modifier
                        .offset(y = -100.dp)

                )

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
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        },
                            modifier = Modifier.padding(end=20.dp)) {
                            Icon(modifier = Modifier
                                .size(30.dp),
                                    painter = icon,

                                    contentDescription = "Visiblity Icon",
                            )
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),

                    singleLine = true,
                    visualTransformation = if(passwordVisibility) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                ClickableText(
                    text = AnnotatedString("Forgot Password?"),
                    onClick = { },
                    modifier = Modifier.align(Alignment.Start),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("Log in", color = Color.Black)
                }
                Spacer(modifier = Modifier.height(8.dp))

                ClickableText(
                    text = AnnotatedString("Create an account"),
                    onClick = { },
                    modifier = Modifier
                        .offset(x=0.dp),
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


