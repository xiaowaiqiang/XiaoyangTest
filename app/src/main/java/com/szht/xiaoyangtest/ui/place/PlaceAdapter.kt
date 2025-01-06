package com.szht.xiaoyangtest.ui.place

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.szht.xiaoyangtest.databinding.ItemPlaceBinding
import com.szht.xiaoyangtest.logic.model.Place

class PlaceAdapter(private val list:List<Place>): RecyclerView.Adapter<PlaceViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
    return PlaceViewHolder(ItemPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun getItemCount(): Int {
    return list.size
  }

  override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
    val place = list[position]
    holder.binding.tvName.text = place.name
    holder.binding.tvAddress.text = place.address
  }
}

class PlaceViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
}
