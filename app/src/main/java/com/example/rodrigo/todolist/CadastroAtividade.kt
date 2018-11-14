package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_atividade.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroAtividade : AppCompatActivity() {

    companion object {
        const val ATIVIDADE: String = "Atividade"
    }

    var atividade: Atividade? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atividade)

        atividade = intent.getSerializableExtra(ATIVIDADE) as Atividade?
        if (atividade!=null){
            carregaDados()
        }

        SalvaAtividade.setOnClickListener{
            salvarCadastro()
        }

    }

    private fun carregaDados() {
        NomeAtividade.setText(atividade?.Nome)
    }

    private fun salvarCadastro() {

        //verifica se o usuário digitou o nome da atividade
        if(NomeAtividade.text.isEmpty()){
            NomeAtividade.requestFocus()
            NomeAtividade.setError(getString(R.string.campo_obrigatorio))
            return
        }

        if (atividade == null){
             atividade = Atividade(NomeAtividade.text.toString())
        }else {
            atividade?.Nome = NomeAtividade.text.toString()
        }

        val atividadeDAO:AtividadeDAO = AppDataBase.getInstance(this).atividadeDAO()
        doAsync {
            atividadeDAO.insert(atividade!!)
            uiThread {
                finish()
            }
        }
    }

}
