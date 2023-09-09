package com.virtualynx.newsviewer.ui.source

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.virtualynx.newsviewer.databinding.FragmentSourceBinding

class SourceFragment : Fragment() {

    private var _binding: FragmentSourceBinding? = null

    private var _adapter: SourceRvAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter get() = _adapter!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val sourceViewModel = ViewModelProvider(this).get(SourceViewModel::class.java)

        _binding = FragmentSourceBinding.inflate(inflater, container, false)
        _adapter = SourceRvAdapter()
        val root: View = binding.root

        val rvSource = binding.rvSource
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
//        rvSource.layoutManager = LinearLayoutManager(requireContext())
        rvSource.layoutManager = llm
        rvSource.adapter = _adapter

//        val testSources = listOf(
//            SourceModel("test1", "test name 1"),
//            SourceModel("test2", "test name 2"),
//            SourceModel("test3", "test name 3")
//        )
//
//        rvSource.adapter = SourceRvAdapter(testSources)

        sourceViewModel.sources.observe(viewLifecycleOwner, Observer {
            if (it!=null && it.isNotEmpty()){
//                rv_home.visibility = View.VISIBLE
//                adapter.setData(it as ArrayList<PostModel>)

//                binding.sourceList.adapter = ArrayAdapter(
//                    requireContext(),
//                    R.layout.simple_list_item_1,
//                    it.stream().map{ a -> a.name}.toList()
//                )

//                binding.sourceList.adapter = SourceArrayAdapter(requireActivity(), it)
                adapter.setData(it)
            }else{
                println("Something went wrong")

                Toast.makeText(requireActivity(), "Cannot fetch source list", Toast.LENGTH_LONG).show()
            }
            rvSource.visibility = View.VISIBLE
            binding.progressSource.visibility = View.GONE
        })

        sourceViewModel.fetchSources()

//        binding.sourceList.setOnItemClickListener { adapterView, view, position, id ->
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
//            println("id: $id, itemAtPos: $itemAtPos, itemIdAtPos: $itemIdAtPos")
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}