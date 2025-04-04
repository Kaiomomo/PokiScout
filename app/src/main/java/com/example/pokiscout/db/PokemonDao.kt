package com.example.pokiscout.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokiscout.Pokemons

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insertPokemon(pokemon: Pokemons)

    @Query("SELECT * FROM pokemon_table WHERE name LIKE :name LIMIT 1")
    suspend fun getPokemonByName(name: String): Pokemons?
}