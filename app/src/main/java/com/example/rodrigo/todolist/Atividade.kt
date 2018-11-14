package com.example.rodrigo.todolist

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
data class Atividade (
//    @ColumnInfo(name = "nome")
    var Nome: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0): Serializable