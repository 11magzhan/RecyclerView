package com.example.recyclerview

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.provider.ContactsContract

class ContactProvider(private val context: Application) {
    @SuppressLint("Range")
    fun getContacts(): List<ContactModel>{
        val contactList: ArrayList<ContactModel> = ArrayList()
        val cursor = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.let {
            if (it.moveToFirst()) {
                do {
                    val name = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneNumber = it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val avatarUri = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI))
                    val contactInfo = ContactModel(
                        photo = if (avatarUri != null) Uri.parse(avatarUri) else null,
                        name = name,
                        number = phoneNumber
                    )
                    contactList.add(contactInfo)
                } while (it.moveToNext())
            }
            it.close()
        }
        cursor?.close()
        return contactList
    }
}