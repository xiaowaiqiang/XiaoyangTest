package com.szht.xiaoyangtest.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.szht.xiaoyangtest.databinding.FragmentPlaceBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class PlaceFragment : Fragment() {

  private var _binding: FragmentPlaceBinding? = null
  private val binding get() = _binding!!
  private val viewModel by lazy { ViewModelProvider(this)[PlaceViewModel::class.java] }
  private lateinit var adapter: PlaceAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentPlaceBinding.inflate(inflater, container, false)
    return binding.root

  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    adapter = PlaceAdapter(viewModel.placeList)
    binding.rvPlace.layoutManager = LinearLayoutManager(activity)
    binding.rvPlace.adapter = adapter

    binding.etQuery.addTextChangedListener { editable ->
      val query = editable.toString()
      if (query.isNotEmpty()) {
        viewModel.searchPlaces(query)
      } else {
        viewModel.placeList.clear()
        adapter.notifyDataSetChanged()
      }
    }
    viewModel.placeLiveData.observe(viewLifecycleOwner){result->
      val places = result.getOrNull()
      if (places != null){
        viewModel.placeList.clear()
        viewModel.placeList.addAll(places)
        adapter.notifyDataSetChanged()
      }else {
        Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
        result.exceptionOrNull()?.printStackTrace()
      }
    }
  }


  override fun onResume() {
    super.onResume()
  }

  override fun onPause() {
    super.onPause()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}