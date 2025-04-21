package com.example.pokiscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pokiscout.db.PokemonDatabase
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, database: PokemonDatabase) {
    var textFieldState by remember { mutableStateOf("") }
    var searchResult by remember { mutableStateOf<Pokemons?>(null) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ABOUT US",
                    modifier = Modifier.clickable {
                        navController.navigate(PokiRoutes.aboutUS)
                    },
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "HOME",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "LOG IN",
                    modifier = Modifier.clickable {
                        navController.navigate(PokiRoutes.LogIn)
                    },
                    color = Color.Red,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokeball1),
                    contentDescription = "PokeBall1",
                    modifier = Modifier.size(35.dp)
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.linkedin),
                        contentDescription = "LinkedIn",
                        modifier = Modifier.size(25.dp).clickable { }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.facebooklogo),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(20.dp).clickable { }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.instagramlogo),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(20.dp).clickable { }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState,
                onValueChange = { textFieldState = it },
                placeholder = { Text("Search for Pokémon") },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier.clickable {
                            performSearch(textFieldState, database) { result, imageRes, error ->
                                if (result != null && imageRes != null) {
                                    searchResult = result
                                    navController.navigate(
                                        "details_screen/${result.name}/${result.ability}/${result.location}/${result.games}/$imageRes"
                                    )
                                } else {
                                    showError = error
                                }
                            }
                        }
                    )
                },
                trailingIcon = {
                    if (textFieldState.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear Search",
                            modifier = Modifier.clickable { textFieldState = "" }
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        performSearch(textFieldState, database) { result, imageRes, error ->
                            if (result != null && imageRes != null) {
                                searchResult = result
                                navController.navigate(
                                    "details_screen/${result.name}/${result.ability}/${result.location}/${result.games}/$imageRes"
                                )
                            } else {
                                showError = error
                            }
                        }
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Image(
                painter = painterResource(id = R.drawable.pika),
                contentDescription = "Pikachu",
                modifier = Modifier
                    .size(50.dp)
                    .offset(y = -115.dp)
            )

            if (showError) {
                Text(
                    text = "No Pokémon found!",
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }
        }
    }
}

// ✅ Must be defined above or below HomeScreen in same file
fun performSearch(
    query: String,
    database: PokemonDatabase,
    callback: (Pokemons?, Int?, Boolean) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val result = database.pokemonDao().getPokemonByName(query)
        val imageRes = when (query.lowercase()) {
            "charizard" -> R.drawable.charizard
            "pikachu" -> R.drawable.pikachu
            else -> R.drawable.instagramlogo
        }

        withContext(Dispatchers.Main) {
            if (result != null) {
                callback(result, imageRes, false)
            } else {
                callback(null, null, true)
            }
        }
    }
}





