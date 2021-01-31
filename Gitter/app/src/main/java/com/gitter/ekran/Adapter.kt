package com.gitter.ekran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitter.R
import com.gitter.data.Repozytorium
import kotlinx.android.synthetic.main.item_list.view.*

class Adapter(private val callback: (Repozytorium) -> Unit): RecyclerView.Adapter<Adapter.ViewHolder>(){

    //lista objektów repozytoriów
    private val listaRepozytoriow = ArrayList<Repozytorium>()

    //funkcja do odswiezania listy repozytoriów "fillData"
    fun setRepozytoria(list: Collection<Repozytorium>){
        for(item: Repozytorium in list)
//        {
//            println(item.nazwa)
//        }
        listaRepozytoriow.clear()
        listaRepozytoriow.addAll(list)
        this.notifyDataSetChanged() //odswieża widok i wyświetla nowe dane
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaRepozytoriow.size
    }

    //wywoływana gdy adapter towrzy i wyświetla liste itemów
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repozytorium = listaRepozytoriow.get(position)
        holder?.itemView?.tytul.text = repozytorium.nazwa
        holder?.itemView?.opis.text = repozytorium.opis
        holder?.itemView?.gwiazdki.text = repozytorium.gwiazdki.toString() + " stars"

        holder?.itemView?.setOnClickListener {
            callback.invoke(repozytorium)
        }
    }

    //przekazujemy item_list.xml jako parametr View
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }
}