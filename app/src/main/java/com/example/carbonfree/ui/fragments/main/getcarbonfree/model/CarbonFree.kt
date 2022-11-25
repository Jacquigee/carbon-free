package com.example.carbonfree.ui.fragments.main.getcarbonfree.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CarbonFree(
    val id: Int,
    val carbonFreeName: String,
    val carbonFreeLottie: Int,
    val carbonFreeNotificationLargeIcon: Int
) : Parcelable
