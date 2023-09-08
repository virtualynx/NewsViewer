package com.virtualynx.newsviewer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.virtualynx.newsviewer.databinding.FragmentHomeBinding

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
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

//        val categoryAdapter: ArrayAdapter<*>
        val categories = arrayOf(
            "All",
            "Business",
            "Entertainment",
            "General",
            "Health",
            "Science",
            "Sport",
            "Technology"
        )
//        val categoryAdapter: ArrayAdapter<*> = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            categories
//            )

        binding.categoryList.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            categories
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}