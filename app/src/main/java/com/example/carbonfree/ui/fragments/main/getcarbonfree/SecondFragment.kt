package com.example.carbonfree.ui.fragments.main.getcarbonfree

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.work.*
import com.example.carbonfree.R
import com.example.carbonfree.databinding.FragmentSecondBinding
import com.example.carbonfree.ui.fragments.main.getcarbonfree.adapter.CarbonFreeAdapter
import com.example.carbonfree.ui.fragments.main.getcarbonfree.model.CarbonFree
import com.example.carbonfree.ui.fragments.main.notifications.notif_workmanager.MyWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class SecondFragment : Fragment(), CarbonFreeAdapter.ClickListener,
    SharedPreferences.OnSharedPreferenceChangeListener {

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
            CarbonFree(
                1,
                "Recycle",
                R.raw.recycle_icon_animation,
                R.drawable.recycle_notif_large_icon,
                "The simplest solution to reduce waste is to adapt our production methods to our consumption patterns. The recycling process must also be taken into account in our consumption habits."
            ),
            CarbonFree(2, "Save Water", R.raw.water_bottle, R.drawable.save_water_notif_large_icon,
            "Turn off the water taps properly after use."),
            CarbonFree(
                3,
                "Grow Trees",
                R.raw.growing_plant,
                R.drawable.grow_trees_notif_large_icon,
                "Grab some seeds or seedlings, and start planting trees like there’s no tomorrow. This will make a big difference. Throughout its life, a tree can absorb and store up to a ton of CO2."
            ),

            CarbonFree(
                4,
                "Clean Energy",
                R.raw.save_energy,
                R.drawable.clean_energy_notif_large_icon,
                "Whenever you can, advocate for clean alternatives to fossil fuels, such as wind, solar, geothermal, and appropriately designed hydroelectric and biomass energy projects."
            ),
            CarbonFree(
                5,
                "Low Carbon Vehicles",
                R.raw.electric_car,
                R.drawable.low_carbon_vehicles_notif_large_icon,
                "When possible, walk or ride your bike in order to avoid carbon emissions completely. Carpooling and public transportation drastically reduce CO2 emissions by spreading them out over many riders."
            ),

            CarbonFree(
                6,
                "Organic Foods",
                R.raw.gardenernergy,
                R.drawable.organic_foods_notif_large_icon,
                "It has been estimated that 13% of U.S. greenhouse gas emissions result from the production and transport of food."
            ),
            CarbonFree(
                7,
                "Eco-Friendly Products",
                R.raw.sustainable_consume,
                R.drawable.eco_friendly_notif_large_icon,
                "Plastic shopping bags from the grocery store (or any retail store) produce even more waste than single use plastic produce bags."
            ),
            CarbonFree(
                8,
                "Minimize Food Waste",
                R.raw.food_animation,
                R.drawable.mini_food_waste_notif_large_icon,
                "Take stock of your pantry, refrigerator and freezer before going to the store to prevent overbuying."
            ),
            CarbonFree(
                9,
                "Cut Out Plastic",
                R.raw.vp_greenify_the_earth,
                R.drawable.cut_out_plastic_notif_large_icon,
                "If you go shopping, remember to take a cloth bag"
            ),
            CarbonFree(
                10,
                "Safe Air Travel",
                R.raw.no_place_like_home_flight,
                R.drawable.safe_air_travel_notif_large_icon,
                "Take fewer and longer vacations that are far away, and more frequent and driveable “staycations” closer to home."
            )

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

    override fun onMyItemClick(carbonFree: CarbonFree) {

        createNotifTest()

        val action = SecondFragmentDirections.actionSecondFragmentToDetailsFragment(carbonFree)
        findNavController().navigate(action)
    }

    fun createNotifTest() {
        val myCarbonFree = carbonFreeList.random()
        val myRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(20, TimeUnit.SECONDS)
            .setInputData(
                workDataOf(
                    "contentTitle" to myCarbonFree.carbonFreeName.toString(),
                    "contentText" to myCarbonFree.carbonFreeLottie.toString(),
                    "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
                )
            ).build()

        WorkManager.getInstance(requireContext()).enqueue(myRequest)
    }

    fun createNotification() {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // step 2 get the value using the 'key'
        val autoNotificationTime =
            sharedPreferences.getString(getString(R.string.notification_time), "")
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
                        "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
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
                        "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
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
                        "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
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
                        "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
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
                        "largeIcon" to myCarbonFree.carbonFreeNotificationLargeIcon
                    )
                ).build()

            WorkManager.getInstance(requireContext()).enqueue(myRequest)
        }


    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
//        createNotification()
        Log.d("secondFragment", "Value changed in settings so i was called")
        Log.d("secondFragment", "Value changed in settings so i was called $p0")
        Log.d("secondFragment", "Value changed in settings so i was called $p1")


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}
