package com.virtualynx.newsviewer.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.databinding.FragmentCategoryBinding
import com.virtualynx.newsviewer.databinding.FragmentHomeBinding
import com.virtualynx.newsviewer.model.CategoryModel
import com.virtualynx.newsviewer.ui.source.SourceAdapter

class CategoryFragment : Fragment(), CategoryItemClickListener {

    private var _binding: FragmentCategoryBinding? = null

    private var _adapter: CategoryAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter get() = _adapter!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _adapter = CategoryAdapter(requireContext(), this)
        binding.lvCategory.adapter = _adapter

//        binding.lvCategory.adapter = ArrayAdapter(
//            requireContext(),
//            android.R.layout.simple_list_item_1,
//            arrayOf(
//                "All",
//                "Business",
//                "Entertainment",
//                "General",
//                "Health",
//                "Science",
//                "Sport",
//                "Technology"
//            )
//        )

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
//                binding.sourceList.adapter = ArrayAdapter(
//                    requireContext(),
//                    android.R.layout.simple_list_item_1,
//                    it.stream().map{ a -> a.name}.toList()
//                )

                adapter.setData(it)
            }else{
                println("Something went wrong")

                Toast.makeText(requireActivity(), "Cannot fetch source list", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.categories.value = listOf(
            CategoryModel("", "All"),
            CategoryModel("business", "Business"),
            CategoryModel("entertainment", "Entertainment"),
            CategoryModel("general", "General"),
            CategoryModel("health", "Health"),
            CategoryModel("science", "Science"),
            CategoryModel("sport", "Sport"),
            CategoryModel("technology", "Technology"),
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(view: View, category: CategoryModel?) {
        val bundle = bundleOf("categoryId" to category?.id)
        findNavController().navigate(R.id.action_nav_to_source, bundle)
    }
}