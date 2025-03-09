package com.example.pokiscout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokiscout.Pokemons

@Database(entities = [Pokemons::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
