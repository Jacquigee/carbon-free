package com.example.carbonfree.ui.fragments.main.getcarbonfree

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.carbonfree.R
import com.example.carbonfree.databinding.FragmentSecondBinding
import com.example.carbonfree.ui.fragments.main.getcarbonfree.adapter.CarbonFreeAdapter
import com.example.carbonfree.ui.fragments.main.getcarbonfree.model.CarbonFree
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment(), CarbonFreeAdapter.ClickListener {

    private lateinit var _binding: FragmentSecondBinding
    private val binding get() = _binding!!

    private lateinit var carbonFreeList: MutableList<CarbonFree>

    private val carbonFreeAdapter: CarbonFreeAdapter by lazy { CarbonFreeAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val myList = mutableListOf<CarbonFree>(
            CarbonFree(1, "Recycle", R.raw.recycle_icon_animation),
            CarbonFree(2, "Save Water", R.raw.water_bottle),
            CarbonFree(3, "Grow Trees", R.raw.growing_plant),

            CarbonFree(4, "Clean Energy", R.raw.save_energy),
            CarbonFree(5, "Low Carbon Vehicles", R.raw.electric_car),

            CarbonFree(6, "Organic Foods", R.raw.gardenernergy),
            CarbonFree(7, "Eco-Friendly Products", R.raw.sustainable_consume),
            CarbonFree(8, "Minimize Food Waste", R.raw.food_animation),
            CarbonFree(9, "Cut Out Plastic", R.raw.vp_greenify_the_earth),
            CarbonFree(10, "Safe Air Travel", R.raw.no_place_like_home_flight)

        )
        carbonFreeList = myList

        setUpRecyclerView()





        carbonFreeAdapter.differ.submitList(carbonFreeList)

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = carbonFreeAdapter
            setHasFixedSize(true)
        }
    }

    private fun addDataToList() {

    }

    override fun onMyItemClick(carbonFree: CarbonFree) {
        Toast.makeText(
            requireContext(),
            "${carbonFree.carbonFreeName} was clicked",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}