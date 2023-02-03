package com.example.capstoneproject3.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Order(
    val id: String? = null, //pano yung search. yung kanina ginagawa mo baliw pinanuod ko yun kanina sa yt yung hinahanap ko ay d nakikinig ctrl + shift + r
    @ServerTimestamp
    val dateOfSubmission: Date? = null,
    val total: Int? = null
)