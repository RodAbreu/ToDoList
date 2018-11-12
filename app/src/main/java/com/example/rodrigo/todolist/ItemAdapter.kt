package com.example.rodrigo.todolist

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rodrigo.todolist.R.id.btnDone
import kotlinx.android.synthetic.main.view_holder.view.*


//adapter gerencia os dados da recycler view
class ItemAdapter(val context: Context, val atividades: List<Atividade>)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    //salva a funcao do clique no item
    var clique: ((index: Int) -> Unit)? = null

    //salva a função do clique no botao done do item
    var cliqueDone: ((index: Int) -> Unit)? = null

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
        holder.bindView(context, atividades[position], clique, cliqueDone)
    }

    fun setOnClickListener(clique: ((index: Int) -> Unit)){
        this.clique = clique
    }

    fun setOnCliqueDone(cliqueDone: ((index: Int) -> Unit)){
        this.cliqueDone = cliqueDone
    }

    //trabalha cada item do recycler view, setando as informações de cada item
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(context: Context,
                     atividade: Atividade,
                     clique: ((index:Int) -> Unit)?,
                     cliqueDone: ((index: Int) -> Unit)?){

            //itemView representa um item da lista
            itemView.nomeNovaAtividade.text = atividade.Nome

            if (clique != null){
                itemView.setOnClickListener(){
                    clique.invoke(adapterPosition)

                }
            }

            if (cliqueDone != null){
                itemView.btnDone.setOnClickListener(){
                    cliqueDone.invoke(adapterPosition)
                }
            }

        }
    }


}