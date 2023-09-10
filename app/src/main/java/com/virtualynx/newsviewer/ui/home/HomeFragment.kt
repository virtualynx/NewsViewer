package com.virtualynx.newsviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.virtualynx.newsviewer.databinding.FragmentHomeBinding
import com.virtualynx.newsviewer.R;

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.homeList.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            arrayOf(
                "List Article By Category",
                "List Article By Source",
                "Search Article",
                "Favorite Article"
            )
        )

        val homeList = binding.homeList

        homeList.setOnItemClickListener { adapterView, view, position, id ->
            // Handle item click here
            val selectedItemId = id // You can get the ID of the clicked item if needed
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            // Navigate to the destination fragment
            if(position == 0){
                findNavController().navigate(R.id.action_nav_to_category)
            }else if(position == 1){
                findNavController().navigate(R.id.action_nav_to_source)
            }else if(position == 2){
                findNavController().navigate(R.id.action_nav_to_search)
            }else if(position == 3){
                findNavController().navigate(R.id.action_nav_to_favorite)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}