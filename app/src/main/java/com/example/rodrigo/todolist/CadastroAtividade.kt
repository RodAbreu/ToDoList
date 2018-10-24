package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro_atividade.*

class CadastroAtividade : AppCompatActivity() {

    companion object {
        const val EXTRA_NOVA_ATIVIDADE: String = "novaAtividade"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_atividade)

        SalvaAtividade.setOnClickListener{
            salvarCadastro()
        }

    }


    private fun salvarCadastro() {

        val stringTest = NomeAtividade.text.toString()
        val atividade = Atividade(stringTest)

        val salvaCadastro = Intent(this, MainActivity::class.java)
        salvaCadastro.putExtra(EXTRA_NOVA_ATIVIDADE, stringTest)
        setResult(Activity.RESULT_OK, salvaCadastro)
        finish()
    }
}
