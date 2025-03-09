package com.example.pokiscout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class Pokemons(
    @PrimaryKey val name: String,
    val ability: String,
    val location: String,
    val games: String,
    )
