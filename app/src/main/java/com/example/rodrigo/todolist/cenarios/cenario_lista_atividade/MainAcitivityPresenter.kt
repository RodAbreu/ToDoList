package com.example.rodrigo.todolist.cenarios.cenario_lista_atividade

import android.content.Context
import com.example.rodrigo.todolist.database.AppDataBase
import com.example.rodrigo.todolist.entidades.Atividade
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainAcitivityPresenter(val view: MainAcitivityContract.View):
    MainAcitivityContract.Presenter {

    override fun onAtualizaLista(context: Context){
        val atividadeDAO = AppDataBase.getInstance(context).atividadeDAO()
        doAsync {
            val atividadesList = atividadeDAO.getAll() as MutableList<Atividade>
            uiThread {
                view.exibeLista(atividadesList)
            }
        }
    }

    override fun onDeletaAtividade(context: Context, atividade: Atividade){
        val atividadeDAO = AppDataBase.getInstance(context).atividadeDAO()
        doAsync {
            atividadeDAO.delete(atividade)
            uiThread {
                onAtualizaLista(context)
            }
        }
    }

}