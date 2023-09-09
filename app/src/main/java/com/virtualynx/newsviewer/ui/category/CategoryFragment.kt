package com.virtualynx.newsviewer.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.virtualynx.newsviewer.databinding.FragmentCategoryBinding
import com.virtualynx.newsviewer.databinding.FragmentHomeBinding

class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.categoryList.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            arrayOf(
                "All",
                "Business",
                "Entertainment",
                "General",
                "Health",
                "Science",
                "Sport",
                "Technology"
            )
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}