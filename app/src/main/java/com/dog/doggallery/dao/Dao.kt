package com.dog.doggallery.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dog.doggallery.entities.Dog
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Insert()
    fun insert(user: Dog)

    @Query("SELECT * FROM perros")
    fun getAll(): Flow<List<Dog>>
    @Query("SELECT * FROM perros WHERE name = :name limit 1")
    fun findByName(name: String,): Dog?
    @Query("Delete from perros WHERE name = :name ")
    suspend fun delete(name: String)
}