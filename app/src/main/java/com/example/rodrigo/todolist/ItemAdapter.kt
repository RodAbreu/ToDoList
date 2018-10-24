package com.example.rodrigo.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_holder.view.*

class ItemAdapter(val atividades: List<String>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return atividades.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(atividades[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(NomeAtividade: String) {
            itemView.nomeNovaAtividade.text = NomeAtividade
        }

    }
}