package com.example.carbonfree.ui.fragments.main.help

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.carbonfree.R
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.TimeUnit


class HelpFragment : PreferenceFragmentCompat() {


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val sharePref =  findPreference<Preference>("share")
        sharePref?.setOnPreferenceClickListener {
//            Toast.makeText(requireContext(), "MEHH", Toast.LENGTH_SHORT).show()

            val action = HelpFragmentDirections.actionHelpFragmentToModalBottomSheetFragment()
            findNavController().navigate(action)

            true
        }

        /// reading pref values
        // step 1 get reference to the shared pref

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // step 2 get the value using the 'key'
        val autoNotificationTime = sharedPreferences.getString(getString(R.string.notification_time), "")
//        Toast.makeText(requireContext(), "$autoNotificationTime", Toast.LENGTH_SHORT).show()
        Log.d("FRAGMENT", "$autoNotificationTime")


        // on pref change
        val autoNotificationTimePref = findPreference<ListPreference>("notification_time")
        autoNotificationTimePref?.setOnPreferenceChangeListener { preference, newValue ->

            if (autoNotificationTime?.toInt() == newValue.toString().toInt()) {

                showSnackBar("New Entry Cant Be The Same As Old Entry")

                 false

            } else {

                showSnackBar("Notification Interval Updated")

                true
            }
        }
    }

    fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(
            requireView(),
            message,
            Snackbar.LENGTH_SHORT
        )

        snackBar.setAction("OKAY"){}
        snackBar.show()
    }

}