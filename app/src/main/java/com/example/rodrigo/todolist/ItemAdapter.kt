package com.example.rodrigo.todolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_holder.view.*


//adapter gerencia os dados da recycler view
class ItemAdapter(val atividades: List<String>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    //metodo responsavel por inflar as views (xmls)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return ViewHolder(view)
    }

    //retorna a quantidade de itens da lista (atividades)
    override fun getItemCount(): Int {
        return atividades.size
    }

    //popula a view holder com as informações das atividades (seleciona qual posicao eu desejo alterar)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(atividades[position])
    }

    //trabalha cada item do recycler view, setando as informações de cada item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(atividadeNome: String) {
            itemView.nomeNovaAtividade.text = atividadeNome
        }
    }


}