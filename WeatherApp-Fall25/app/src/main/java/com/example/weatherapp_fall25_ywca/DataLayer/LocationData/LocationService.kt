package com.example.weatherapp_fall25_ywca.DataLayer.LocationData

import android.Manifest
import android.content.Context
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationService(context: Context) {

    private val locationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getCurrentLocation(): Location? {
        return suspendCoroutine { cont->
            val token = CancellationTokenSource().token
            locationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                token
            ).addOnSuccessListener { location ->
                cont.resume(location)
            }
                .addOnFailureListener {
                    cont.resume(null)
                }
        }
    }

}