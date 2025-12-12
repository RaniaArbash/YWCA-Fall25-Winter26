package com.example.weatherapp_fall25_ywca.DataLayer.CloudDatabase

import com.google.firebase.firestore.FirebaseFirestore

object FirebaseInstance {
    val database: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
}