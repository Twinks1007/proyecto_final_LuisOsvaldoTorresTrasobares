package com.dog.doggallery.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dog.doggallery.dao.DogDao
import com.dog.doggallery.entities.Dog
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Dog::class],
    version = 2
) abstract class DogRoomDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao companion object {

        @Volatile
        private var INSTANCE: DogRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ):DogRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogRoomDatabase::class.java,
                    "dog.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }


        }

    }
}