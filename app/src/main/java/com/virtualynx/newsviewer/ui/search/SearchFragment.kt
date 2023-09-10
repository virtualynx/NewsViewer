package com.virtualynx.newsviewer.ui.search

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
import androidx.room.Room
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.databinding.FragmentSearchBinding
import com.virtualynx.newsviewer.entity.ArticleEntity
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.service.AppDatabase
import com.virtualynx.newsviewer.ui.source.SourceViewModel
import kotlin.streams.toList

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sourceViewModel.sources.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
                binding.spinnerSource.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    it.stream().map { a -> a.name }.toList()
                )
            }else if(it.isEmpty()){
                Toast.makeText(requireActivity(), "No source found", Toast.LENGTH_LONG).show()
            }else{
                println("Something went wrong")

                Toast.makeText(requireActivity(), "Cannot fetch source list", Toast.LENGTH_LONG).show()
            }
            binding.progress.visibility = View.GONE
        })

//        sourceViewModel.fetch();

        binding.btnSearch.setOnClickListener {
            val position = binding.spinnerSource.selectedItemPosition

            val source = sourceViewModel.sources.value?.get(position)
            val article = binding.editTextArticle.text

            if(source == null){
                Toast.makeText(requireActivity(), "Source must be filled", Toast.LENGTH_LONG).show()
            }else{
                val bundle = bundleOf(
                    "sourceId" to source.id,
                    "sourceName" to source.name,
                    "q" to article.toString()
                )
                findNavController().navigate(R.id.action_nav_to_article, bundle)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}