package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.Item

@Database(entities = arrayOf(Item::class), version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}