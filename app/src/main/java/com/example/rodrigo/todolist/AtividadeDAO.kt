package com.example.rodrigo.todolist

import android.arch.persistence.room.*

@Dao
interface AtividadeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(atividade: Atividade)

    @Query("SELECT * FROM atividade")
    fun getAll(): List<Atividade>

    @Delete
    fun delete(atividade: Atividade)

    @Query("SELECT * FROM atividade WHERE id = :atividadeId LIMIT 1")
    fun getAtividade(atividadeId: Int): Atividade

    @Query("SELECT * FROM atividade WHERE nome like :nomeAtividade")
    fun buscaPeloNome(nomeAtividade: String): List<Atividade>
}