package com.sandiarta.todoapp.model

import android.icu.text.CaseMap.Title
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo: Todo)

    @Query("SELECT * FROM todo ORDER BY is_done ASC, priority DESC")
    fun selectAllTodo():List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    fun selectTodo(id:Int):Todo

    @Delete
    fun deleteTodo(todo:Todo)

    @Query("UPDATE todo SET title=:title, notes=:notes,priority=:priority WHERE uuid=:id")
    fun update(title: String, notes:String, priority:Int,id:Int)

    @Query("UPDATE todo SET is_done=:isDone WHERE uuid=:id")
    fun updateIsDone(isDone:Int,id:Int)

    @Update
    fun updateTodo(todo: Todo)

}