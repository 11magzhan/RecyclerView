package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemContactBinding

interface UserActionsListener {
    fun onCallClicked(contact: ContactModel)
}

class ContactAdapter(val contactList: ArrayList<ContactModel>, private val actionListener: UserActionsListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(), View.OnClickListener {

    inner class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: ContactModel) {
            binding.tvName.text = contact.name
            binding.tvNumber.text = contact.number
            if (contact.photo != null) {
                binding.ivPhoto.setImageURI(contact.photo)
            } else {
                binding.ivPhoto.setImageResource(R.drawable.phone_call)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.setOnClickListener(this)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun onClick(v: View?) {
        actionListener.onCallClicked(contactList[0])
    }
}