package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemContactBinding


class ContactAdapter(
    private val actionListener: (ContactModel) -> Unit
) : RecyclerView.Adapter<ContactViewHolder>() {
    private val contactList: MutableList<ContactModel> = mutableListOf()

    fun setItems(items: List<ContactModel>) {
        val diffResult = DiffUtil.calculateDiff(ContactDiffCallback(contactList, items))
        contactList.clear()
        contactList.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) =
        holder.bind(contactList[position], actionListener)
}