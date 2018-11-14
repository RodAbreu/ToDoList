package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_holder.*
import org.jetbrains.anko.activityUiThreadWithContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1
        private const val ATIVIDADES = "ListaAtividades"
    }

    private var atividadesList: MutableList<Atividade> = mutableListOf()

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
        carregaLista()
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

    fun carregaLista() {

        val atividadeDAO = AppDataBase.getInstance(this).atividadeDAO()
        doAsync {
            atividadesList = atividadeDAO.getAll() as MutableList<Atividade>

            activityUiThreadWithContext {
                val adapter = ItemAdapter(this, atividadesList)

                adapter.setOnClickListener() { indexAtividadeClicada ->
                    val editaAtividade = Intent(this, CadastroAtividade::class.java)
                    editaAtividade.putExtra(CadastroAtividade.ATIVIDADE, atividadesList.get(indexAtividadeClicada))
                    startActivity(editaAtividade)
                }

                adapter.setOnCliqueDone { indexAtividadeClicada ->
                    doAsync {
                        atividadeDAO.delete(atividadesList.get(indexAtividadeClicada))
                        uiThread {
                            carregaLista()
                            true
                        }
                    }
                }

                val layoutManager = LinearLayoutManager(this)

                rvList.adapter = adapter
                rvList.layoutManager = layoutManager
            }
        }
    }
}


