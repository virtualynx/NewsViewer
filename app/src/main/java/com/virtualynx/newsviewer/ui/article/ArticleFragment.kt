package com.virtualynx.newsviewer.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.databinding.FragmentArticleBinding
import com.virtualynx.newsviewer.model.ArticleModel

class ArticleFragment : Fragment(), ArticleItemClickListener {

    private var _binding: FragmentArticleBinding? = null

    private var _adapter: ArticleAdapter? = null

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

        _adapter = ArticleAdapter(this)
        binding.rvArticle.adapter = _adapter

        viewModel.articles.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
                adapter.setData(it)
            }else if(it.isEmpty()){
                Toast.makeText(requireActivity(), "No article found", Toast.LENGTH_LONG).show()
            }else{
                println("Something went wrong")

                Toast.makeText(requireActivity(), "Cannot fetch source list", Toast.LENGTH_LONG).show()
            }
            binding.rvArticle.visibility = View.VISIBLE
            binding.progressArticle.visibility = View.GONE
        })

        val sourceId = arguments?.getString("sourceId").toString()
        val sourceName = arguments?.getString("sourceName").toString()
        val q = arguments?.getString("q")

        var title: String = "Source - $sourceName"
        if(!q.isNullOrEmpty()){
            title += " (Key: $q)"
        }
        (activity as AppCompatActivity).supportActionBar?.title = title

        if(!q.isNullOrEmpty()){
            viewModel.fetch(sourceId, q)
        }else{
            viewModel.fetch(sourceId)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }

    override fun onItemClicked(view: View, article: ArticleModel?) {
        println("Clicked article: $article")

        val bundle = bundleOf("url" to article?.url)
        findNavController().navigate(R.id.action_nav_to_article_read, bundle)
    }
}