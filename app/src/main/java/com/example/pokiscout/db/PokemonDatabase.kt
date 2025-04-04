package com.example.pokiscout.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokiscout.Pokemons
import com.example.pokiscout.User

@Database(entities = [Pokemons::class, User::class], version = 2)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun userDao(): UserDao
}
