package com.example.rodrigo.todolist.cenarios.cenario_cadastro_atividade

import android.content.Context
import com.example.rodrigo.todolist.database.AppDataBase
import com.example.rodrigo.todolist.entidades.Atividade
import com.example.rodrigo.todolist.database.AtividadeDAO
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroAtividadePresenter (val view: CadastroAtividadeContract.View):
    CadastroAtividadeContract.Presenter {

    override fun onSalvaAtividade(context: Context, atividade: Atividade){
        val atividadeDAO: AtividadeDAO = AppDataBase.getInstance(
            context
        ).atividadeDAO()
        doAsync {
            atividadeDAO.insert(atividade)
            uiThread {
                view.salvoComSucesso()
            }
        }
    }



}