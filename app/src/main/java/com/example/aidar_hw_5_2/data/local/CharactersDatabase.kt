package com.example.aidar_hw_5_2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun dao(): CharactersDao
}