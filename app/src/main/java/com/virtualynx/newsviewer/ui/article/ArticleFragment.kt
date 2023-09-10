package com.virtualynx.newsviewer.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.virtualynx.newsviewer.databinding.FragmentArticleBinding
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.model.SourceModel

class ArticleFragment : Fragment(), ArticleItemClickListener {

    private var _binding: FragmentArticleBinding? = null

    private var _adapter: ArticleRvAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter get() = _adapter!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _adapter = ArticleRvAdapter(this)
        binding.rvArticle.adapter = _adapter

        viewModel.articles.observe(viewLifecycleOwner, Observer {
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
            binding.rvArticle.visibility = View.VISIBLE
            binding.progressArticle.visibility = View.GONE
        })

        arguments?.getString("sourceId")?.let { viewModel.fetch(it) }

//        binding.lvSource.setOnItemClickListener { adapterView, view, position, id ->
//            // Handle item click here
//            val selectedItemId = id // You can get the ID of the clicked item if needed
//            val itemAtPos = adapterView.getItemAtPosition(position)
//            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
//
//            // Navigate to the destination fragment
////            findNavController().navigate(com.virtualynx.newsviewer.R.id.action_nav_to_category)
////            val action = .actionToDestinationFragment()
////            findNavController().navigate(action)
//
////            println("id: $id, itemAtPos: $itemAtPos, itemIdAtPos: $itemIdAtPos")
//
//            val selectedModel = sourceViewModel.sources.value?.get(position)
//            println("selectedModel: $selectedModel")
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }

    override fun onItemClicked(view: View, article: ArticleModel?) {
        println("Clicked source: $article")
    }
}