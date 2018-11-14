package com.example.rodrigo.todolist.cenarios.cenario_cadastro_atividade

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rodrigo.todolist.entidades.Atividade
import com.example.rodrigo.todolist.R
import kotlinx.android.synthetic.main.activity_cadastro_atividade.*

class CadastroAtividade : AppCompatActivity(),
    CadastroAtividadeContract.View {

    companion object {
        const val ATIVIDADE: String = "Atividade"
    }

    var atividade: Atividade? = null

    val presenter: CadastroAtividadeContract.Presenter =
        CadastroAtividadePresenter(this)

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

        atividade?.let {atividade ->
            presenter.onSalvaAtividade(this, atividade)
        }
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
    }

    override fun salvoComSucesso(){
        Toast.makeText(this, "Atividade salva com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }

}
