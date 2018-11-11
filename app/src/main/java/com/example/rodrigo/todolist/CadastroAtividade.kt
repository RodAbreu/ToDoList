package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_atividade.*

class CadastroAtividade : AppCompatActivity() {

    companion object {
        const val ATIVIDADE: String = "Atividade"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atividade)

        val atividade = intent.getSerializableExtra(ATIVIDADE) as Atividade?
        if (atividade!=null){
            carregaDados(atividade)
        }

        SalvaAtividade.setOnClickListener{
            salvarCadastro()
        }

    }

    private fun carregaDados(atividade: Atividade) {
        NomeAtividade.setText(atividade.Nome)
    }

    private fun salvarCadastro() {

        //verifica se o usuário digitou o nome da atividade
        if(NomeAtividade.text.isEmpty()){
            NomeAtividade.requestFocus()
            NomeAtividade.setError(getString(R.string.campo_obrigatorio))
            return
        }

        val stringTest = NomeAtividade.text.toString()
        val atividade = Atividade(stringTest)
        val salvaCadastro = Intent(this, MainActivity::class.java)
        salvaCadastro.putExtra(ATIVIDADE, atividade)
        setResult(Activity.RESULT_OK, salvaCadastro)
        finish()
    }
}
