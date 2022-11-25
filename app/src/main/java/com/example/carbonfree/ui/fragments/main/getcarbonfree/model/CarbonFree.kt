package com.example.carbonfree.ui.fragments.main.getcarbonfree.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CarbonFree(
    val id: Int,
    val carbonFreeName: String,
<<<<<<< HEAD
    val carbonFreeLottie: Int,
    val carbonFreeNotificationLargeIcon: Int
=======
    val carbonFreeLottie: Int
>>>>>>> d45e9f51a1419791e5357e9761a036d566a67f79
) : Parcelable
