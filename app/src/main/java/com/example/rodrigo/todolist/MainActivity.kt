package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1
        private const val ATIVIDADES = "ListaAtividades"
    }

    private var atividadesList: MutableList<Atividade> = mutableListOf()

    var indexToDo: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnAddItem.setOnClickListener(){
            val cadastrarItem = Intent(this, CadastroAtividade::class.java)
            startActivityForResult(cadastrarItem, REQUEST_CADASTRO)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK){
            val novaAtividade: Atividade? = data?.getSerializableExtra(CadastroAtividade.ATIVIDADE) as Atividade
            if (novaAtividade != null) {
                //adiciona na lista a atividade
                atividadesList.add(novaAtividade)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putSerializable(ATIVIDADES, atividadesList as ArrayList<String>)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null){
            atividadesList = savedInstanceState.getSerializable(ATIVIDADES) as MutableList<Atividade>
        }
    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    fun carregaLista() {
        val adapter = ItemAdapter(atividadesList)
        val layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        rvList.adapter = adapter
        rvList.layoutManager = layoutManager
        rvList.addItemDecoration(dividerItemDecoration)
    }


}
