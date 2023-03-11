package com.example.spacex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rockets(
    val id: String,
    val active: Boolean,
    val rocketName: String,
    val firstFlight: String,
    val country: String,
    val image: String,
    val wikipedia: String,
    val description: String
) : Parcelable {
    val firstFlightFormatted: String
    get() = "First flight: $firstFlight"
}