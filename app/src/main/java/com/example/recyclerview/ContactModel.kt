package com.example.recyclerview

import android.net.Uri

data class ContactModel(
    val name: String,
    val number: String,
    val photo: Uri?
)
