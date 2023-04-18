package edu.wccnet.imullison.contactsproject.ui.main

import android.telephony.PhoneNumberUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.wccnet.imullison.contactsproject.Contact
import edu.wccnet.imullison.contactsproject.R

class ContactListAdapter(
    private val contactItemLayout: Int,
    private val deleteClickHandler: (contactId: Int) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var contactList: List<Contact>? = null

    override fun onBindViewHolder(holder: ViewHolder, listPosition: Int) {

        contactList!!.let {
            holder.nameTextView.text = it[listPosition].contactName
            holder.phoneTextView.text =
                PhoneNumberUtils.formatNumber(it[listPosition].contactPhone.toString(), "US")
        }
        holder.deleteIcon.setOnClickListener {
            deleteClickHandler(contactList!![listPosition].id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(contactItemLayout, parent, false)
        return ViewHolder(view)
    }

    fun setContactList(contacts: List<Contact>) {
        contactList = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (contactList == null) 0 else contactList!!.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView = itemView.findViewById(R.id.contactName)
        var phoneTextView: TextView = itemView.findViewById(R.id.contactPhone)
        var deleteIcon: ImageView = itemView.findViewById(R.id.deleteButton)
    }
}
