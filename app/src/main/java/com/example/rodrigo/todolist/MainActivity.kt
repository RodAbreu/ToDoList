package com.example.rodrigo.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1
    }

    private val atividadesList: MutableList<String> = mutableListOf()
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
            val novaAtividade: String? = data?.getStringExtra(CadastroAtividade.EXTRA_NOVA_ATIVIDADE)
            if (novaAtividade != null) {
                atividadesList.add(novaAtividade)
            }
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
