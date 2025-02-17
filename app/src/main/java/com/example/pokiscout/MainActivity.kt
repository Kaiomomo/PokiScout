package com.example.pokiscout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults


import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokiscout.ui.theme.PokiScoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokiScoutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var searchPokemon by remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color(0xFFFFFFFF)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                        ) {
                        TextField(
                            shape = RoundedCornerShape(50.dp),
                            colors= OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFF808080),
                                focusedContainerColor = Color(0xFF808080),
                                cursorColor = Color(0xFFFFFFFF),
                                focusedPlaceholderColor = Color(0xFFFFFFFF),
                                unfocusedPlaceholderColor = Color(0xFFFFFFFF),


                                ),
                            value = searchPokemon,

                            placeholder = {

                                Text("search a pokemon")

                                    },
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Item")

                            },
                            onValueChange = {
                                searchPokemon= it
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()

                        )

                    }
                }
            }
        }
    }
}


