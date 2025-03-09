package com.example.pokiscout

import android.app.Application
import androidx.room.Room
import com.example.pokiscout.db.PokemonDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainApplication : Application() {

    val database: PokemonDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            insertSampleData()
        }
    }

    private suspend fun insertSampleData() {
        val pokemonDao = database.pokemonDao()

        // Check if Charizard already exists
        if (pokemonDao.getPokemonByName("Charizard") == null) {
            pokemonDao.insertPokemon(Pokemons("Charizard", "Blaze", "Kanto", "Red, Blue"))
        }

        // Check if Pikachu already exists
        if (pokemonDao.getPokemonByName("Pikachu") == null) {
            pokemonDao.insertPokemon(Pokemons("Pikachu", "Static", "Kanto", "Yellow"))
        }
    }
}



