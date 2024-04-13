package com.example.jsonparserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var items: List<PokemonItem>): RecyclerView.Adapter<RecyclerAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.textView2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val current = items[position]
        holder.textView.text = current.name.english
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateData(newNames: List<String>) {
        items = newNames.mapIndexed { index, name ->
            PokemonItem(index, PokemonItem.Name(name, ""))
        }
        notifyDataSetChanged()
    }
}