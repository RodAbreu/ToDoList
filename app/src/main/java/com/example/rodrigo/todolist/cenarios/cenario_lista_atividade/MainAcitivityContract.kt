package com.example.rodrigo.todolist.cenarios.cenario_lista_atividade

import android.content.Context
import com.example.rodrigo.todolist.entidades.Atividade

interface MainAcitivityContract {

    interface View{
        fun exibeLista(lista: MutableList<Atividade>)
    }

    interface Presenter{
        fun onAtualizaLista(context: Context)
        fun onDeletaAtividade(context: Context, atividade: Atividade)
    }



}