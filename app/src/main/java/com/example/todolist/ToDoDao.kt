package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Item)

    @Delete
    suspend fun delete(todo: Item)

    @Query("SELECT * from toDoTable order by id ASC")
    fun getAllTodos(): LiveData<List<Item>>

    @Query("UPDATE toDoTable set title = :title, note = :note where id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)
}