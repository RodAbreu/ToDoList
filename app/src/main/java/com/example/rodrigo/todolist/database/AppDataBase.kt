package com.example.rodrigo.todolist.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.rodrigo.todolist.entidades.Atividade

@Database(entities = arrayOf(Atividade::class), version = 1)
public abstract class AppDataBase: RoomDatabase (){

    companion object {

        private val DB_NAME = "atividade.db"
        private var instance: AppDataBase? = null

        private fun create(context: Context): AppDataBase? {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                DB_NAME
            ).build()
        }

        public fun getInstance(context: Context): AppDataBase {
            if (instance == null)
                instance =
                        create(context)
            return instance!!
        }
    }

    public abstract fun atividadeDAO(): AtividadeDAO
}