package com.example.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemContactBinding

class ContactViewHolder(private val binding: ItemContactBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: ContactModel, actionListener: (ContactModel) -> Unit) {
        binding.root.setOnClickListener{
            actionListener.invoke(contact)
        }
        binding.tvName.text = contact.name
        binding.tvNumber.text = contact.number
        if (contact.photo != null) {
            binding.ivPhoto.setImageURI(contact.photo)
        } else {
            binding.ivPhoto.setImageResource(R.drawable.phone_call)
        }
    }
}