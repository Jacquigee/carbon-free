package com.example.carbonfree.ui.fragments.main.help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.carbonfree.R


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
    }
}