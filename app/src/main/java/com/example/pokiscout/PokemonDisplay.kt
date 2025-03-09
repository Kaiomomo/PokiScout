package com.example.pokiscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PokemonDetailScreen(navController: NavController,name: String, ability: String, location: String, games: String, imageResId:Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Name: $name", style = TextStyle(fontWeight = FontWeight.Bold))
        Text(text = "Ability: $ability")
        Text(text = "Location: $location")
        Text(text = "Games: $games")
    }

    Button(
        onClick = { navController.popBackStack() },
        colors = ButtonDefaults.buttonColors(Color.Red),
        modifier = Modifier.fillMaxWidth(0.7f)
            .offset(y=800.dp)
    ) {
        Text("Exit", color = Color.Black)
    }
}
