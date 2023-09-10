package com.virtualynx.newsviewer.ui.article.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.virtualynx.newsviewer.databinding.FragmentArticleBinding
import com.virtualynx.newsviewer.databinding.FragmentArticleReadBinding
import com.virtualynx.newsviewer.model.ArticleModel

class ArticleReadFragment : Fragment() {

    private var _binding: FragmentArticleReadBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleReadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments?.getString("url")?.let {
            val webView = binding.webView

            webView.webViewClient = WebViewClient()

            // this will load the url of the website
            webView.loadUrl(it)

            // this will enable the javascript settings, it can also allow xss vulnerabilities
            webView.settings.javaScriptEnabled = true

            // if you want to enable zoom feature
            webView.settings.setSupportZoom(true)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}