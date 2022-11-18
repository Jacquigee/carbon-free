package com.example.carbonfree.ui.fragments.main.getcarbonfree

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.carbonfree.R
import com.example.carbonfree.databinding.FragmentSecondBinding
import com.example.carbonfree.ui.fragments.main.getcarbonfree.adapter.CarbonFreeAdapter
import com.example.carbonfree.ui.fragments.main.getcarbonfree.model.CarbonFree
import com.example.carbonfree.ui.fragments.main.notifications.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : Fragment(), CarbonFreeAdapter.ClickListener {

    private lateinit var _binding: FragmentSecondBinding
    private val binding get() = _binding!!

    private lateinit var carbonFreeList: MutableList<CarbonFree>

    private val carbonFreeAdapter: CarbonFreeAdapter by lazy { CarbonFreeAdapter(this) }
    private val notificationViewModel : NotificationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        val myList = mutableListOf<CarbonFree>(
            CarbonFree(1, "Recycle", R.raw.recycle_icon_animation, R.drawable.recycle_notif_large_icon),
            CarbonFree(2, "Save Water", R.raw.water_bottle, R.drawable.save_water_notif_large_icon),
            CarbonFree(3, "Grow Trees", R.raw.growing_plant, R.drawable.grow_trees_notif_large_icon),

            CarbonFree(4, "Clean Energy", R.raw.save_energy, R.drawable.clean_energy_notif_large_icon),
            CarbonFree(5, "Low Carbon Vehicles", R.raw.electric_car, R.drawable.low_carbon_vehicles_notif_large_icon),

            CarbonFree(6, "Organic Foods", R.raw.gardenernergy, R.drawable.organic_foods_notif_large_icon),
            CarbonFree(7, "Eco-Friendly Products", R.raw.sustainable_consume, R.drawable.eco_friendly_notif_large_icon),
            CarbonFree(8, "Minimize Food Waste", R.raw.food_animation, R.drawable.mini_food_waste_notif_large_icon),
            CarbonFree(9, "Cut Out Plastic", R.raw.vp_greenify_the_earth, R.drawable.cut_out_plastic_notif_large_icon),
            CarbonFree(10, "Safe Air Travel", R.raw.no_place_like_home_flight, R.drawable.safe_air_travel_notif_large_icon)

        )
        carbonFreeList = myList

        setUpRecyclerView()

        carbonFreeAdapter.differ.submitList(carbonFreeList)

        lifecycleScope.launchWhenCreated {

//            createNotification()
            checkSharedPrefValues()
        }

        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = carbonFreeAdapter
            setHasFixedSize(true)
        }
    }


    override fun onMyItemClick(carbonFree: CarbonFree) {

        val action = SecondFragmentDirections.actionSecondFragmentToDetailsFragment(carbonFree)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }

    fun createNotification() {

        val notificationItem = carbonFreeList.random()
        val bitmap = BitmapFactory.decodeResource(resources, notificationItem.carbonFreeNotificationLargeIcon)

        notificationViewModel.showSimpleNotificationRecycler(
            notificationItem.carbonFreeName.toString(),
            notificationItem.carbonFreeLottie.toString(),
            bitmap
        )
    }

    fun checkSharedPrefValues() {

        /// reading pref values
        // step 1 get reference to the shared pref

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // step 2 get the value using the 'key'
        val autoNotificationTime = sharedPreferences.getString(getString(R.string.notification_time), "")
        Toast.makeText(requireContext(), "$autoNotificationTime", Toast.LENGTH_SHORT).show()
        Log.d("FRAGMENT", "$autoNotificationTime")
    }
}
