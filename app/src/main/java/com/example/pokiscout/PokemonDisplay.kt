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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
    val backgroundColor = if (name.equals("Pikachu", ignoreCase = true)) Color(0xFFFFFF99) else Color(0xFFFFCDD2)

    Card(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = name,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().offset(y = 40.dp)
                    )

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "$name Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp)
                            .offset(x = 20.dp, y = 30.dp)
                    )
                }

                if (name.equals("Charizard", ignoreCase = true)) {
                    item {
                        Text(
                            text = "About Charizard",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Charizard is a Fire/Flying-type Pokémon known for its dragon-like appearance and powerful flame attacks. It evolves from Charmeleon and is the final form of Charmander.",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    item {
                        Text(
                            text = "Evolutions",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(
                                R.drawable.charmander_charizard to "Charmander",
                                R.drawable.charmealon_charizard to "Charmeleon"
                            ).forEach { (image, desc) ->
                                Image(
                                    painter = painterResource(id = image),
                                    contentDescription = desc,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape)
                                        .border(3.dp, Color.White, CircleShape)
                                )
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Games & Locations",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val charizardGames = listOf(
                            "Red / Blue / Yellow" to "Starter Pokémon (Red/Blue), Trade or evolve Charmander",
                            "Gold / Silver / Crystal" to "Trade from Gen 1 or use Time Capsule",
                            "FireRed / LeafGreen" to "Starter Pokémon (FireRed)",
                            "X / Y" to "Choose as second starter after first gym",
                            "Sun / Moon / Ultra Sun / Ultra Moon" to "Transfer from Pokémon Bank",
                            "Sword / Shield" to "Gift from Leon after becoming Champion",
                            "Brilliant Diamond / Shining Pearl" to "Underground Hideaways (post-game)",
                            "Legends: Arceus" to "Transfer only",
                            "Scarlet / Violet" to "Event or transfer from Pokémon HOME"
                        )

                        charizardGames.forEach { (game, location) ->
                            DetailRowUnderlined(label = game, value = location)
                        }
                    }
                }

                if (name.equals("Pikachu", ignoreCase = true)) {
                    item {
                        Text(
                            text = "About Pikachu",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Pikachu is an Electric-type Pokémon and the iconic mascot of the Pokémon franchise. Known for its yellow fur and lightning bolt-shaped tail, Pikachu evolves from Pichu and can evolve into Raichu.",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    item {
                        Text(
                            text = "Games & Locations",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val pikachuGames = listOf(
                            "Yellow" to "Starter Pokémon",
                            "Red / Blue" to "Viridian Forest",
                            "Gold / Silver / Crystal" to "Viridian Forest (in Yellow only)",
                            "Ruby / Sapphire / Emerald" to "Safari Zone (Emerald)",
                            "Diamond / Pearl / Platinum" to "Trophy Garden",
                            "X / Y" to "Santalune Forest",
                            "Sun / Moon / Ultra Sun / Ultra Moon" to "Route 1",
                            "Sword / Shield" to "Route 4",
                            "Legends: Arceus" to "Obsidian Fieldlands",
                            "Scarlet / Violet" to "South Province (Area Two)"
                        )

                        pikachuGames.forEach { (game, location) ->
                            DetailRowUnderlined(label = game, value = location)
                        }
                    }

                    item {
                        Text(
                            text = "Special Appearance",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .shadow(8.dp, RoundedCornerShape(16.dp))
                                .background(Color.White)
                                .padding(12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.pichu),
                                contentDescription = "Pokemon Yellow Pikachu",
                                modifier = Modifier.size(120.dp)
                            )
                        }
                    }
                }
            }

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Exit",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x=-300.dp,y=20.dp)
                    .padding(16.dp)
                    .clickable { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun DetailRowUnderlined(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1976D2),
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}





