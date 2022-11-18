package com.example.carbonfree.ui.fragments.viewpager.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.carbonfree.R
import com.example.carbonfree.databinding.FragmentFirstScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstScreenFragment : Fragment() {

    private lateinit var _binding: FragmentFirstScreenBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {

            // array, so second fragment = 1

            viewPager?.currentItem = 1
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}