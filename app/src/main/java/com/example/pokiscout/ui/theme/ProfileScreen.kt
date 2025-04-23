package com.example.pokiscout.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokiscout.PokiRoutes
import com.example.pokiscout.User
import com.example.pokiscout.db.PokemonDatabase
import com.example.pokiscout.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, database: PokemonDatabase) {
    val context = LocalContext.current
    var currentUser by remember { mutableStateOf<User?>(null) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val loggedIn = SessionManager.getLoggedInUser(context)
        println("🔍 Logged-in user from SessionManager: $loggedIn")

        if (loggedIn != null) {
            val user = database.userDao().getUser(loggedIn)
            println("🔍 User fetched from DB: $user")

            currentUser = user
            if (user != null) {
                username = user.username
                password = user.password
            }
        } else {
            println("⚠️ No logged-in user found in DataStore.")
        }
    }

    if (currentUser == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading profile...", modifier = Modifier.padding(16.dp))
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFF4B1))
        ) {

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Red,
                modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable {
                        navController.navigate(PokiRoutes.HomeScreen) {
                            popUpTo(0)
                            launchSingleTop = true
                        }
                    }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 48.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🎮 Your Trainer Profile",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFB71C1C)
                )

                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Trainer Name") },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFFB71C1C),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color(0xFFB71C1C),
                        unfocusedIndicatorColor = Color.Gray
                    )
                )

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            currentUser?.let {
                                database.userDao().deleteUserByUsername(it.username)
                                database.userDao().insertUser(User(username, password))
                                SessionManager.saveLoggedInUser(context, username)

                                withContext(Dispatchers.Main) {
                                    navController.popBackStack()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)) // Red
                ) {
                    Text("💾 Save Changes", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            SessionManager.logoutUser(context)
                            withContext(Dispatchers.Main) {
                                navController.navigate(PokiRoutes.LogIn) {
                                    popUpTo(PokiRoutes.HomeScreen) { inclusive = true }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("🚪 Log Out", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
