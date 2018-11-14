package com.example.rodrigo.todolist.cenarios.cenario_cadastro_atividade

import android.content.Context
import com.example.rodrigo.todolist.entidades.Atividade

interface CadastroAtividadeContract {

    interface View {
        fun salvoComSucesso()
    }

    interface Presenter {
        fun onSalvaAtividade(context: Context, atividade: Atividade)
    }



}