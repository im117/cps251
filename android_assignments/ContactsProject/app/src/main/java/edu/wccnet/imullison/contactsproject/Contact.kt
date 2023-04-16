package edu.wccnet.imullison.contactsproject

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="contactId")
    var id: Int = 0

    @ColumnInfo(name="contactName")
    var contactName: String? = null

    @ColumnInfo(name="contactPhone")
    var contactPhone: Long = 0

    constructor()
    constructor(contactname: String, phone: Long) {
        this.contactName = contactname
        this.contactPhone = phone
    }
}