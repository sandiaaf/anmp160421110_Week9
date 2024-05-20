package com.sandiarta.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandiarta.todoapp.util.DB_NAME
import com.sandiarta.todoapp.util.MIGRATION_1_2
import com.sandiarta.todoapp.util.MIGRATION_2_3

@Database(entities = arrayOf(Todo::class), version = 3)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao
    companion object{
        @Volatile private var instance:TodoDatabase?=null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .addMigrations(MIGRATION_2_3)
                .build()

        operator fun invoke(context: Context){
            if(instance != null){
                synchronized(LOCK){
                    instance?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}

//make dua database
//@Database(entities = arrayOf(Todo::class,History::class), version = 1)
//abstract class TodoDatabase:RoomDatabase() {
//
//}