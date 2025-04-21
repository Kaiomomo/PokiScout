package com.example.pokiscout.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Profile",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

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
                    .height(50.dp)
            ) {
                Text("Save Changes")
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
                    .height(50.dp)
            ) {
                Text("Log Out")
            }
        }
    }
}
