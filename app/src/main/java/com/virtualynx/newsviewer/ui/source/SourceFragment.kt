package com.virtualynx.newsviewer.ui.source

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.virtualynx.newsviewer.databinding.FragmentSourceBinding
import kotlin.streams.toList

class SourceFragment : Fragment() {

    private var _binding: FragmentSourceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)

        _binding = FragmentSourceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sourceViewModel.fetchSources()

//        val textView: TextView = binding.textGallery
//        sourceViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        sourceViewModel.sources.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
//                rv_home.visibility = View.VISIBLE
//                adapter.setData(it as ArrayList<PostModel>)

                val sourceNames = it.stream().map{ a -> a.name}.toList()
                binding.sourceList.adapter = ArrayAdapter(
                    requireContext(),
                    R.layout.simple_list_item_1,
                    sourceNames
                )

//                binding.sourceList.adapter = SourceArrayAdapter(requireActivity(), it)
            }else{
//                showToast("Something went wrong")
                println("Something went wrong")
            }
//            progress_home.visibility = View.GONE
        })

        binding.sourceList.setOnItemClickListener { adapterView, view, position, id ->
            // Handle item click here
            val selectedItemId = id // You can get the ID of the clicked item if needed
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            // Navigate to the destination fragment
            findNavController().navigate(com.virtualynx.newsviewer.R.id.action_nav_to_category)
//            val action = .actionToDestinationFragment()
//            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}