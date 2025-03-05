package com.example.pokiscout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokiscout.ui.theme.PokiScoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokiScoutTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = PokiRoutes.HomeScreen , builder = {
                        composable (PokiRoutes.HomeScreen,){
                            HomeScreen(navController)
                        }

                    composable (PokiRoutes.aboutUS,){
                        aboutUS(navController)

                    }
                    composable (PokiRoutes.LogIn,){
                        LogIn(navController)

                    }

                } )


            }
        }
    }
}

