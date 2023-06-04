package com.tutkuozbakir.kripto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutkuozbakir.kripto.databinding.RowBinding
import com.tutkuozbakir.kripto.model.CryptoModel

class RecyclerViewAdapter(private val kriptoList: ArrayList<CryptoModel>) : RecyclerView.Adapter<RecyclerViewAdapter.Holder>(){

    class Holder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.rowText.text = "${kriptoList.get(position).currency}\n${kriptoList.get(position).price}"
    }

    override fun getItemCount(): Int {
        return kriptoList.size
    }

}