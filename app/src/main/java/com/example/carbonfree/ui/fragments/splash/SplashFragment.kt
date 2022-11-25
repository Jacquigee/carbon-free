package com.example.carbonfree.ui.fragments.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.carbonfree.databinding.FragmentSplashBinding
import com.example.carbonfree.datastore.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var _binding: FragmentSplashBinding
    private val binding get() = _binding!!

    private val myViewModel: DataStoreViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        myViewModel.retrieveData()

        Handler().postDelayed({

            myViewModel.showTheData().observe(viewLifecycleOwner, Observer {
                val myFinished = it.finished

                if (myFinished) {
                    val action = SplashFragmentDirections.actionSplashFragmentToMainActivity()
                    findNavController().navigate(action)
                }
                else {
                    val action = SplashFragmentDirections.actionSplashFragmentToViewPagerFragment()
                    findNavController().navigate(action)


                }

            })

        }, 3000)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}