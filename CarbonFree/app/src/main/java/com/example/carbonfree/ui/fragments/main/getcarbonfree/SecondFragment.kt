package com.example.carbonfree.ui.fragments.main.getcarbonfree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.example.carbonfree.R
import com.example.carbonfree.databinding.FragmentSecondBinding
import com.example.carbonfree.ui.fragments.main.getcarbonfree.adapter.CarbonFreeAdapter
import com.example.carbonfree.ui.fragments.main.getcarbonfree.model.CarbonFree
import com.example.carbonfree.ui.fragments.main.notifications.notif_workmanager.MyWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


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

        createNotification()

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

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // step 2 get the value using the 'key'
        val autoNotificationTime = sharedPreferences.getString(getString(R.string.notification_time), "")
//        Toast.makeText(requireContext(), "$autoNotificationTime", Toast.LENGTH_SHORT).show()

        val myCarbonFree = carbonFreeList.random()

        if (autoNotificationTime?.toInt() == 6) {
            val myRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(
                6, TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(
                        "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                        "contentText" to myCarbonFree.carbonFreeLottie.toString(),
//                    "bitmap" to carbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }
        if (autoNotificationTime?.toInt() == 12) {
            val myRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(
                12, TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(
                        "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                        "contentText" to myCarbonFree.carbonFreeLottie.toString(),
//                    "bitmap" to carbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }

        if (autoNotificationTime?.toInt() == 18) {
            val myRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(
                18, TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(
                        "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                        "contentText" to myCarbonFree.carbonFreeLottie.toString(),
//                    "bitmap" to carbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }

        if (autoNotificationTime?.toInt() == 24) {
            val myRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(
                24, TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(
                        "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                        "contentText" to myCarbonFree.carbonFreeLottie.toString(),
//                    "bitmap" to carbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }

        if (autoNotificationTime?.toInt() == 48) {
            val myRequest: WorkRequest = PeriodicWorkRequestBuilder<MyWorker>(
                48, TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(
                        "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                        "contentText" to myCarbonFree.carbonFreeLottie.toString(),
//                    "bitmap" to carbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }


    }

}
