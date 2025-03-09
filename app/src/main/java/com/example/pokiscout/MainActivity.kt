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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokiscout.db.PokemonDatabase
import com.example.pokiscout.ui.theme.PokiScoutTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokiScoutTheme {
                val navController = rememberNavController()

                val database = (applicationContext as MainApplication).database

                NavHost(
                    navController = navController,
                    startDestination = PokiRoutes.HomeScreen
                ) {
                    composable(PokiRoutes.HomeScreen) {
                        HomeScreen(navController = navController, database = database)
                    }
                    composable(
                        "details_screen/{name}/{ability}/{location}/{games}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType; defaultValue = "Unknown" },
                            navArgument("ability") { type = NavType.StringType; defaultValue = "Unknown" },
                            navArgument("location") { type = NavType.StringType; defaultValue = "Unknown" },
                            navArgument("games") { type = NavType.StringType; defaultValue = "Unknown" }
                        )
                    ) { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
                        val ability = backStackEntry.arguments?.getString("ability") ?: "Unknown"
                        val location = backStackEntry.arguments?.getString("location") ?: "Unknown"
                        val games = backStackEntry.arguments?.getString("games") ?: "Unknown"

                        PokemonDetailScreen(navController,name, ability, location, games)
                    }

                    composable(PokiRoutes.aboutUS) {
                        aboutUS(navController)
                    }

                    composable(PokiRoutes.LogIn) {
                        LogIn(navController)
                    }
                }
            }
        }
    }
}



