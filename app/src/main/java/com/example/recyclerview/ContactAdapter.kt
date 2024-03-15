package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemContactBinding

interface UserActionsListener {
    fun onCallClicked(contact: ContactModel)
}

class ContactAdapter(
    private val contactList: ArrayList<ContactModel>,
    private val actionListener: UserActionsListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(contact: ContactModel, position: Int) {
            binding.root.setOnClickListener(this)
            binding.tvName.text = contact.name
            binding.tvNumber.text = contact.number
            if (contact.photo != null) {
                binding.ivPhoto.setImageURI(contact.photo)
            } else {
                binding.ivPhoto.setImageResource(R.drawable.phone_call)
            }
            binding.root.tag = position
        }

        override fun onClick(v: View?) {
            val position = v?.tag as? Int ?: RecyclerView.NO_POSITION
            if (position != RecyclerView.NO_POSITION) {
                actionListener.onCallClicked(contactList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) = holder.bind(contactList[position], position)

}