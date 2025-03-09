package com.example.pokiscout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokiscout.db.PokemonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, database: PokemonDatabase) {
    var textFieldState by remember { mutableStateOf("") }
    var searchResult by remember { mutableStateOf<Pokemons?>(null) }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Text(
                text = "HOME",
                modifier = Modifier
                    .offset(x = 190.dp, y = 50.dp)
                    .clickable { },
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            Text(
                text = "ABOUT US",
                modifier = Modifier
                    .offset(x = 25.dp, y = 50.dp)
                    .clickable { navController.navigate(PokiRoutes.aboutUS) },
                style = TextStyle(fontWeight = FontWeight.Bold)
            )

            Text(
                text = "LOG IN",
                modifier = Modifier
                    .offset(x = 345.dp, y = 50.dp)
                    .clickable { navController.navigate(PokiRoutes.LogIn) },
                color = Color.Red,
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        },
        bottomBar = {
            Image(
                painter = painterResource(id = R.drawable.pokeball1),
                contentDescription = "PokeBall1",
                modifier = Modifier
                    .size(35.dp)
                    .offset(x = 20.dp, y = -10.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.instagramlogo),
                contentDescription = "InstaGramLogo",
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 380.dp, y = -8.dp)
                    .clickable { }
            )
            Image(
                painter = painterResource(id = R.drawable.facebooklogo),
                contentDescription = "FaceBookLogo",
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = 350.dp, y = -8.dp)
                    .clickable { }
            )
            Image(
                painter = painterResource(id = R.drawable.linkedin),
                contentDescription = "FaceBookLogo",
                modifier = Modifier
                    .size(25.dp)
                    .offset(x = 320.dp, y = -9.dp)
                    .clickable { }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            TextField(
                value = textFieldState,
                placeholder = { Text("Search for Pokemon") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "PokiSearch",
                        modifier = Modifier.clickable {
                            performSearch(textFieldState, database) { result, error ->
                                if (result != null) {
                                    searchResult = result
                                    navController.navigate(
                                        "details_screen/${result.name}/${result.ability}/${result.location}/${result.games}"
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
                            modifier = Modifier.clickable { textFieldState = "" },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close-PokiSearch"
                        )
                    }
                },
                onValueChange = { textFieldState = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )

            )

            if (showError) {
                Text(
                    text = "No Pokémon found!",
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.pika),
                contentDescription = "PikaSearch",
                modifier = Modifier
                    .size(50.dp)
                    .offset(x = -7.dp, y = -115.dp)
            )
        }
    }
}

private fun performSearch(
    query: String,
    database: PokemonDatabase,
    callback: (Pokemons?, Boolean) -> Unit
) {
    CoroutineScope(Dispatchers.IO).launch {
        val result = database.pokemonDao().getPokemonByName(query)
        withContext(Dispatchers.Main) {
            if (result != null) {
                callback(result, false)
            } else {
                callback(null, true)
            }
        }
    }
}


