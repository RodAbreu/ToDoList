package com.example.rodrigo.todolist.cenarios.cenario_lista_atividade

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.rodrigo.todolist.entidades.Atividade
import com.example.rodrigo.todolist.R
import com.example.rodrigo.todolist.cenarios.cenario_cadastro_atividade.CadastroAtividade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainAcitivityContract.View {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1
        private const val ATIVIDADES = "ListaAtividades"
    }

    private var atividadesList: MutableList<Atividade> = mutableListOf()

    val presenter: MainAcitivityContract.Presenter =
        MainAcitivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAddItem.setOnClickListener() {
            val cadastrarAtividade = Intent(this, CadastroAtividade::class.java)
            startActivity(cadastrarAtividade)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onAtualizaLista(this)
    }

    //quando o usuário gira o celular
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ATIVIDADES, atividadesList as ArrayList<String>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            atividadesList = savedInstanceState.getSerializable(ATIVIDADES) as MutableList<Atividade>
        }
    }

    //nao tinha esses parametros
    override fun exibeLista(lista: MutableList<Atividade>) {

        atividadesList = lista

        val adapter = ItemAdapter(this, atividadesList)

        adapter.setOnClickListener() { indexAtividadeClicada ->
            val editaAtividade = Intent(this, CadastroAtividade::class.java)
            editaAtividade.putExtra(CadastroAtividade.ATIVIDADE, atividadesList.get(indexAtividadeClicada))
            startActivity(editaAtividade)
        }

        adapter.setOnCliqueDone { indexAtividadeClicada ->
            presenter.onDeletaAtividade(this,
                atividadesList.get(indexAtividadeClicada))
            true
        }

        val layoutManager = LinearLayoutManager(this)

        rvList.adapter = adapter
        rvList.layoutManager = layoutManager
            }
}


