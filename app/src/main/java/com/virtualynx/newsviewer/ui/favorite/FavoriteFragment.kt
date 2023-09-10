package com.virtualynx.newsviewer.ui.favorite

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
import com.virtualynx.newsviewer.databinding.FragmentFavoriteBinding
import com.virtualynx.newsviewer.model.ArticleModel

class FavoriteFragment : Fragment(), FavoriteItemClickListener {

    private var _binding: FragmentFavoriteBinding? = null

    private var _adapter: FavoriteAdapter? = null

    private var _favoriteViewModel: FavoriteViewModel?= null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter get() = _adapter!!
    private val favoriteViewModel get() = _favoriteViewModel!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _adapter = FavoriteAdapter(this)
        binding.rvFavorite.adapter = _adapter

        favoriteViewModel.articles.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
                adapter.setData(it)
            }else if(it.isEmpty()){
                Toast.makeText(requireActivity(), "No article found", Toast.LENGTH_LONG).show()
            }else{
                println("Something went wrong")

                Toast.makeText(requireActivity(), "Cannot fetch source list", Toast.LENGTH_LONG).show()
            }
            binding.rvFavorite.visibility = View.VISIBLE
            binding.progressArticle.visibility = View.GONE
        })

        favoriteViewModel.fetch(requireContext())

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

    override fun onItemFavoriteClicked(view: View, article: ArticleModel?) {
        article?.let {
            favoriteViewModel.delete(requireContext(), it)
            favoriteViewModel.fetch(requireContext())

            Toast.makeText(requireActivity(), "Article deleted from Favorite", Toast.LENGTH_LONG).show()
        }
    }
}