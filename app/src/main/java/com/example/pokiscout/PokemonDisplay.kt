package com.example.pokiscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun PokemonDetailScreen(
    navController: NavController,
    name: String,
    ability: String,
    location: String,
    games: String,
    imageRes: Int
) {
    val backgroundColor = if (name == "Pikachu") Color(0xFFFFFF99) else Color(0xFFFFCDD2)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(11.dp)
            .shadow(10.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFB42020))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    if (name == "Charizard") {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "$name Image",
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "$name",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Ability: $ability",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = location, fontSize = 16.sp)
                        Text(text = "Games found in: $games", fontSize = 16.sp)

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Evolutions",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.charmander_charizard),
                                contentDescription = "Charmander to Charizard",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .border(4.dp, Color.White, CircleShape)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.charmealon_charizard),
                                contentDescription = "Charmeleon to Charizard",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .border(4.dp, Color.White, CircleShape)
                            )
                        }
                    }
                }

                item {
                    if (name == "Pikachu") {
                        Text(
                            text = "Pikachu",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            modifier = Modifier.fillMaxWidth()
                                .offset(y = 50.dp),
                            textAlign = TextAlign.Center
                        )

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "$name Image",
                            modifier = Modifier.fillMaxWidth()
                                .offset(y = 60.dp, x = 20.dp)
                        )
                        Text(
                            text = "Location",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                            modifier = Modifier
                                .offset(x = 145.dp, y = 120.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = -20.dp, y = 140.dp)
                                .shadow(12.dp, RoundedCornerShape(16.dp))
                                .clip(RoundedCornerShape(16.dp))
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.pokemon_yellow),
                                contentDescription = "Pokemon Yellow Pikachu",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Exit",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navController.popBackStack() }
            )
        }
    }
}
